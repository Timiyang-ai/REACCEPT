protected RequestStatusResponse updateComponents(Set<ServiceComponentRequest> requests,
                                                                Map<String, String> requestProperties,
                                                                boolean runSmokeTest)
      throws AmbariException, AuthorizationException {

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return null;
    }

    Clusters clusters = getManagementController().getClusters();
    AmbariMetaInfo ambariMetaInfo = getManagementController().getAmbariMetaInfo();

    Map<State, List<ServiceComponent>> changedComps = new HashMap<>();
    Map<String, Map<State, List<ServiceComponentHost>>> changedScHosts = new HashMap<>();
    Collection<ServiceComponentHost> ignoredScHosts = new ArrayList<>();

    Set<String> clusterNames = new HashSet<>();
    Map<String, Map<String, Set<String>>> componentNames = new HashMap<>();
    Set<State> seenNewStates = new HashSet<>();

    Collection<ServiceComponent> recoveryEnabledComponents = new ArrayList<>();
    Collection<ServiceComponent> recoveryDisabledComponents = new ArrayList<>();

    // Determine operation level
    Resource.Type reqOpLvl;
    if (requestProperties.containsKey(RequestOperationLevel.OPERATION_LEVEL_ID)) {
      RequestOperationLevel operationLevel = new RequestOperationLevel(requestProperties);
      reqOpLvl = operationLevel.getLevel();
    } else {
      LOG.warn("Can not determine request operation level. Operation level property should be specified for this request.");
      reqOpLvl = Resource.Type.Cluster;
    }

    for (ServiceComponentRequest request : requests) {
      Validate.notEmpty(request.getComponentName(), "component name should be non-empty");
      final Cluster cluster = getClusterForRequest(request, clusters);
      final String clusterName = request.getClusterName();
      final String serviceGroupName = request.getServiceGroupName();
      final String serviceName = request.getServiceDisplayName();
      final String componentName = request.getComponentName();

      LOG.info("Received a updateComponent request: {}", request);

      setServiceNameIfAbsent(request, cluster, ambariMetaInfo);

      debug("Received a updateComponent request: {}", request);

      clusterNames.add(clusterName);

      Validate.isTrue(clusterNames.size() == 1, "Updates to multiple clusters is not supported");

      if (!componentNames.containsKey(clusterName)) {
        componentNames.put(clusterName, new HashMap<String, Set<String>>());
      }
      if (!componentNames.get(clusterName).containsKey(serviceName)) {
        componentNames.get(clusterName).put(serviceName, new HashSet<String>());
      }
      if (componentNames.get(clusterName).get(serviceName).contains(componentName)){
        // throw error later for dup
        throw new IllegalArgumentException("Invalid request contains duplicate service components");
      }
      componentNames.get(clusterName).get(serviceName).add(componentName);

      Service s = cluster.getService(serviceName);
      ServiceComponent sc = s.getServiceComponent(componentName);
      State newState = getValidDesiredState(request);

      if (! maintenanceStateHelper.isOperationAllowed(reqOpLvl, s)) {
        LOG.info("Operations cannot be applied to component " + componentName
                + " because service " + serviceName +
                " is in the maintenance state of " + s.getMaintenanceState());
        continue;
      }

      // Gather the components affected by the change in
      // auto start state
      if (!StringUtils.isEmpty(request.getRecoveryEnabled())) {
        // Verify that the authenticated user has authorization to change auto-start states for services
        AuthorizationHelper.verifyAuthorization(ResourceType.CLUSTER, getClusterResourceId(clusterName),
            EnumSet.of(RoleAuthorization.CLUSTER_MANAGE_AUTO_START, RoleAuthorization.SERVICE_MANAGE_AUTO_START));

        boolean newRecoveryEnabled = Boolean.parseBoolean(request.getRecoveryEnabled());
        boolean oldRecoveryEnabled = sc.isRecoveryEnabled();
        LOG.info("Component: {}, oldRecoveryEnabled: {}, newRecoveryEnabled {}",
                componentName, oldRecoveryEnabled, newRecoveryEnabled);
        if (newRecoveryEnabled != oldRecoveryEnabled) {
          if (newRecoveryEnabled) {
            recoveryEnabledComponents.add(sc);
          } else {
            recoveryDisabledComponents.add(sc);
          }
        }
      }

      if (newState == null) {
        debug("Nothing to do for new updateServiceComponent request, request ={}, newDesiredState=null" + request);
        continue;
      }

      if (sc.isClientComponent() && !newState.isValidClientComponentState()) {
        throw new AmbariException("Invalid desired state for a client component");
      }

      seenNewStates.add(newState);

      State oldScState = sc.getDesiredState();
      if (newState != oldScState) {
        // The if user is trying to start or stop the component, ensure authorization
        if (((newState == State.INSTALLED) || (newState == State.STARTED))) {
          isAuthorized(cluster, RoleAuthorization.SERVICE_START_STOP);
        }

        if (!State.isValidDesiredStateTransition(oldScState, newState)) {
          // FIXME throw correct error
          throw new AmbariException("Invalid transition for"
              + " servicecomponent"
              + ", clusterName=" + cluster.getClusterName()
              + ", clusterId=" + cluster.getClusterId()
              + ", serviceGroupName=" + serviceGroupName
              + ", serviceName=" + sc.getServiceName()
              + ", componentName=" + sc.getName()
              + ", recoveryEnabled=" + sc.isRecoveryEnabled()
              + ", currentDesiredState=" + oldScState
              + ", newDesiredState=" + newState);
        }

        if (!changedComps.containsKey(newState)) {
          changedComps.put(newState, new ArrayList<ServiceComponent>());
        }
        debug("Handling update to ServiceComponent"
              + ", clusterName=" + clusterName
              + ", serviceGroupName=" + serviceGroupName
              + ", serviceName=" + serviceName
              + ", componentName=" + sc.getName()
              + ", recoveryEnabled=" + sc.isRecoveryEnabled()
              + ", currentDesiredState=" + oldScState
              + ", newDesiredState=" + newState);

        changedComps.get(newState).add(sc);
      }

      for (ServiceComponentHost sch : sc.getServiceComponentHosts().values()) {
        State oldSchState = sch.getState();
        if (oldSchState == State.DISABLED || oldSchState == State.UNKNOWN) {
          debug("Ignoring ServiceComponentHost"
                + ", clusterName=" + clusterName
                + ", serviceGroupName=" + serviceGroupName
                + ", serviceName=" + serviceName
                + ", componentName=" + sc.getName()
                + ", recoveryEnabled=" + sc.isRecoveryEnabled()
                + ", hostname=" + sch.getHostName()
                + ", currentState=" + oldSchState
                + ", newDesiredState=" + newState);
          continue;
        }

        if (newState == oldSchState) {
          ignoredScHosts.add(sch);
          debug("Ignoring ServiceComponentHost"
                + ", clusterName=" + clusterName
                + ", serviceGroupName=" + serviceGroupName
                + ", serviceName=" + serviceName
                + ", componentName=" + sc.getName()
                + ", recoveryEnabled=" + sc.isRecoveryEnabled()
                + ", hostname=" + sch.getHostName()
                + ", currentState=" + oldSchState
                + ", newDesiredState=" + newState);
          continue;
        }

        // do not update or alter any HC that is not active
        if (! maintenanceStateHelper.isOperationAllowed(reqOpLvl, sch)) {
          ignoredScHosts.add(sch);
          debug("Ignoring ServiceComponentHost in maintenance state"
                + ", clusterName=" + clusterName
                + ", serviceGroupName=" + serviceGroupName
                + ", serviceName=" + serviceName
                + ", componentName=" + sc.getName()
                + ", recoveryEnabled=" + sc.isRecoveryEnabled()
                + ", hostname=" + sch.getHostName());

          continue;
        }

        if (!State.isValidStateTransition(oldSchState, newState)) {
          // FIXME throw correct error
          throw new AmbariException("Invalid transition for"
              + " servicecomponenthost"
              + ", clusterName=" + cluster.getClusterName()
              + ", clusterId=" + cluster.getClusterId()
              + ", serviceGroupName=" + serviceGroupName
              + ", serviceName=" + sch.getServiceName()
              + ", componentName=" + sch.getServiceComponentName()
              + ", recoveryEnabled=" + sc.isRecoveryEnabled()
              + ", hostname=" + sch.getHostName()
              + ", currentState=" + oldSchState
              + ", newDesiredState=" + newState);
        }
        if (!changedScHosts.containsKey(sc.getName())) {
          changedScHosts.put(sc.getName(), new HashMap<State, List<ServiceComponentHost>>());
        }
        if (!changedScHosts.get(sc.getName()).containsKey(newState)) {
          changedScHosts.get(sc.getName()).put(newState, new ArrayList<ServiceComponentHost>());
        }

        debug("Handling update to ServiceComponentHost"
              + ", clusterName=" + clusterName
              + ", serviceGroupName=" + serviceGroupName
              + ", serviceName=" + serviceName
              + ", componentName=" + sc.getName()
              + ", recoveryEnabled=" + sc.isRecoveryEnabled()
              + ", hostname=" + sch.getHostName()
              + ", currentState=" + oldSchState
              + ", newDesiredState=" + newState);

        changedScHosts.get(sc.getName()).get(newState).add(sch);
      }
    }

    Validate.isTrue(seenNewStates.size() <= 1,
        "Cannot handle different desired state changes for a set of service components at the same time");

    // TODO additional validation?

    // Validations completed. Update the affected service components now.

    for (ServiceComponent sc : recoveryEnabledComponents) {
      sc.setRecoveryEnabled(true);
    }

    for (ServiceComponent sc : recoveryDisabledComponents) {
      sc.setRecoveryEnabled(false);
    }

    Cluster cluster = clusters.getCluster(clusterNames.iterator().next());

    return getManagementController().createAndPersistStages(cluster, requestProperties, null, null, changedComps, changedScHosts,
        ignoredScHosts, runSmokeTest, false);
  }