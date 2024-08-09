protected synchronized RequestStatusResponse updateComponents(Set<ServiceComponentRequest> requests,
                                                             Map<String, String> requestProperties, boolean runSmokeTest)
      throws AmbariException, AuthorizationException {

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return null;
    }

    AmbariManagementController controller = getManagementController();
    Clusters clusters = controller.getClusters();
    AmbariMetaInfo ambariMetaInfo = controller.getAmbariMetaInfo();

    Map<State, List<ServiceComponent>> changedComps =
        new HashMap<State, List<ServiceComponent>>();
    Map<String, Map<State, List<ServiceComponentHost>>> changedScHosts =
        new HashMap<String, Map<State, List<ServiceComponentHost>>>();
    Collection<ServiceComponentHost> ignoredScHosts =
        new ArrayList<ServiceComponentHost>();

    Set<String> clusterNames = new HashSet<String>();
    Map<String, Map<String, Set<String>>> componentNames =
        new HashMap<String, Map<String,Set<String>>>();
    Set<State> seenNewStates = new HashSet<State>();

    // Determine operation level
    Resource.Type reqOpLvl;
    if (requestProperties.containsKey(RequestOperationLevel.OPERATION_LEVEL_ID)) {
      RequestOperationLevel operationLevel = new RequestOperationLevel(requestProperties);
      reqOpLvl = operationLevel.getLevel();
    } else {
      String message = "Can not determine request operation level. " +
              "Operation level property should " +
              "be specified for this request.";
      LOG.warn(message);
      reqOpLvl = Resource.Type.Cluster;
    }

    for (ServiceComponentRequest request : requests) {
      final String clusterName = request.getClusterName();
      String componentName = request.getComponentName();
      if (clusterName == null
          || clusterName.isEmpty()
          || componentName == null
          || componentName.isEmpty()) {
        throw new IllegalArgumentException("Invalid arguments, cluster name"
            + ", service name and component name should be provided to"
            + " update components");
      }

      String serviceName = request.getServiceName();
      LOG.info("Received a updateComponent request"
          + ", clusterName=" + clusterName
          + ", serviceName=" + serviceName
          + ", componentName=" + componentName
          + ", request=" + request.toString());

      Cluster cluster = clusters.getCluster(clusterName);

      if (serviceName == null
          || serviceName.isEmpty()) {
        StackId stackId = cluster.getDesiredStackVersion();
        String alternativeServiceName =
            ambariMetaInfo.getComponentToService(stackId.getStackName(),
                stackId.getStackVersion(), componentName);
        if (LOG.isDebugEnabled()) {
          LOG.debug("Looking up service name for component"
              + ", componentName=" + componentName
              + ", serviceName=" + alternativeServiceName);
        }

        if (alternativeServiceName == null
            || alternativeServiceName.isEmpty()) {
          throw new AmbariException("Could not find service for component"
              + ", componentName=" + componentName
              + ", clusterName=" + cluster.getClusterName()
              + ", stackInfo=" + stackId.getStackId());
        }
        request.setServiceName(alternativeServiceName);
        serviceName = alternativeServiceName;
      }

      if (LOG.isDebugEnabled()) {
        LOG.debug("Received a updateComponent request"
                + ", clusterName=" + clusterName
                + ", serviceName=" + serviceName
                + ", componentName=" + componentName
                + ", request=" + request);
      }

      clusterNames.add(clusterName);

      if (clusterNames.size() > 1) {
        // FIXME throw correct error
        throw new IllegalArgumentException("Updates to multiple clusters is not"
            + " supported");
      }

      if (!componentNames.containsKey(clusterName)) {
        componentNames.put(clusterName,
            new HashMap<String, Set<String>>());
      }
      if (!componentNames.get(clusterName)
          .containsKey(serviceName)) {
        componentNames.get(clusterName).put(
                serviceName, new HashSet<String>());
      }
      if (componentNames.get(clusterName)
          .get(serviceName).contains(componentName)){
        // throw error later for dup
        throw new IllegalArgumentException("Invalid request contains duplicate"
            + " service components");
      }
      componentNames.get(clusterName)
          .get(serviceName).add(componentName);

      Service s = cluster.getService(serviceName);
      ServiceComponent sc = s.getServiceComponent(componentName);
      State newState = null;
      if (request.getDesiredState() != null) {
        newState = State.valueOf(request.getDesiredState());
        if (!newState.isValidDesiredState()) {
          throw new IllegalArgumentException("Invalid arguments, invalid"
              + " desired state, desiredState=" + newState.toString());
        }
      }

      if (! maintenanceStateHelper.isOperationAllowed(reqOpLvl, s)) {
        LOG.info("Operations cannot be applied to component " + componentName
                + " because service " + serviceName +
                " is in the maintenance state of " + s.getMaintenanceState());
        continue;
      }

      if (newState == null) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("Nothing to do for new updateServiceComponent request"
              + ", clusterName=" + clusterName
              + ", serviceName=" + serviceName
              + ", componentName=" + componentName
              + ", newDesiredState=null");
        }
        continue;
      }

      if (sc.isClientComponent() &&
          !newState.isValidClientComponentState()) {
        throw new AmbariException("Invalid desired state for a client"
            + " component");
      }

      seenNewStates.add(newState);

      State oldScState = sc.getDesiredState();
      if (newState != oldScState) {
        // The if user is trying to start or stop the component, ensure authorization
        if (((newState == State.INSTALLED) || (newState == State.STARTED)) &&
            !AuthorizationHelper.isAuthorized(ResourceType.CLUSTER, cluster.getResourceId(), RoleAuthorization.SERVICE_START_STOP)) {
          throw new AuthorizationException("The authenticated user is not authorized to start or stop components of services");
        }

        if (!State.isValidDesiredStateTransition(oldScState, newState)) {
          // FIXME throw correct error
          throw new AmbariException("Invalid transition for"
              + " servicecomponent"
              + ", clusterName=" + cluster.getClusterName()
              + ", clusterId=" + cluster.getClusterId()
              + ", serviceName=" + sc.getServiceName()
              + ", componentName=" + sc.getName()
              + ", currentDesiredState=" + oldScState
              + ", newDesiredState=" + newState);
        }
        if (!changedComps.containsKey(newState)) {
          changedComps.put(newState, new ArrayList<ServiceComponent>());
        }
        if (LOG.isDebugEnabled()) {
          LOG.debug("Handling update to ServiceComponent"
              + ", clusterName=" + clusterName
              + ", serviceName=" + serviceName
              + ", componentName=" + sc.getName()
              + ", currentDesiredState=" + oldScState
              + ", newDesiredState=" + newState);
        }
        changedComps.get(newState).add(sc);
      }

      for (ServiceComponentHost sch : sc.getServiceComponentHosts().values()) {
        State oldSchState = sch.getState();
        if (oldSchState == State.DISABLED || oldSchState == State.UNKNOWN) {
          if (LOG.isDebugEnabled()) {
            LOG.debug("Ignoring ServiceComponentHost"
                + ", clusterName=" + clusterName
                + ", serviceName=" + serviceName
                + ", componentName=" + sc.getName()
                + ", hostname=" + sch.getHostName()
                + ", currentState=" + oldSchState
                + ", newDesiredState=" + newState);
          }
          continue;
        }

        if (newState == oldSchState) {
          ignoredScHosts.add(sch);
          if (LOG.isDebugEnabled()) {
            LOG.debug("Ignoring ServiceComponentHost"
                + ", clusterName=" + clusterName
                + ", serviceName=" + serviceName
                + ", componentName=" + sc.getName()
                + ", hostname=" + sch.getHostName()
                + ", currentState=" + oldSchState
                + ", newDesiredState=" + newState);
          }
          continue;
        }

        // do not update or alter any HC that is not active
        if (! maintenanceStateHelper.isOperationAllowed(reqOpLvl, sch)) {
          ignoredScHosts.add(sch);
          if (LOG.isDebugEnabled()) {
            LOG.debug("Ignoring ServiceComponentHost in maintenance state"
                + ", clusterName=" + clusterName
                + ", serviceName=" + serviceName
                + ", componentName=" + sc.getName()
                + ", hostname=" + sch.getHostName());
          }
          continue;
        }

        if (!State.isValidStateTransition(oldSchState, newState)) {
          // FIXME throw correct error
          throw new AmbariException("Invalid transition for"
              + " servicecomponenthost"
              + ", clusterName=" + cluster.getClusterName()
              + ", clusterId=" + cluster.getClusterId()
              + ", serviceName=" + sch.getServiceName()
              + ", componentName=" + sch.getServiceComponentName()
              + ", hostname=" + sch.getHostName()
              + ", currentState=" + oldSchState
              + ", newDesiredState=" + newState);
        }
        if (!changedScHosts.containsKey(sc.getName())) {
          changedScHosts.put(sc.getName(),
              new HashMap<State, List<ServiceComponentHost>>());
        }
        if (!changedScHosts.get(sc.getName()).containsKey(newState)) {
          changedScHosts.get(sc.getName()).put(newState,
              new ArrayList<ServiceComponentHost>());
        }
        if (LOG.isDebugEnabled()) {
          LOG.debug("Handling update to ServiceComponentHost"
              + ", clusterName=" + clusterName
              + ", serviceName=" + serviceName
              + ", componentName=" + sc.getName()
              + ", hostname=" + sch.getHostName()
              + ", currentState=" + oldSchState
              + ", newDesiredState=" + newState);
        }
        changedScHosts.get(sc.getName()).get(newState).add(sch);
      }
    }

    if (seenNewStates.size() > 1) {
      // FIXME should we handle this scenario
      throw new IllegalArgumentException("Cannot handle different desired"
          + " state changes for a set of service components at the same time");
    }

    // TODO additional validation?

    Cluster cluster = clusters.getCluster(clusterNames.iterator().next());

    return getManagementController().createAndPersistStages(cluster, requestProperties, null, null, changedComps, changedScHosts,
        ignoredScHosts, runSmokeTest, false);
  }