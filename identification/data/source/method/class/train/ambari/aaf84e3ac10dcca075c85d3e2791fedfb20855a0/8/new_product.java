protected synchronized RequestStageContainer updateHostComponents(RequestStageContainer stages,
                                                                    Set<ServiceComponentHostRequest> requests,
                                                                    Map<String, String> requestProperties,
                                                                    boolean runSmokeTest) throws AmbariException {

    Clusters clusters = getManagementController().getClusters();


    Map<String, Map<State, List<ServiceComponentHost>>> changedScHosts = new HashMap<String, Map<State, List<ServiceComponentHost>>>();
    Collection<ServiceComponentHost> ignoredScHosts = new ArrayList<ServiceComponentHost>();
    Set<String> clusterNames = new HashSet<String>();
    Map<String, Map<String, Map<String, Set<String>>>> requestClusters = new HashMap<String, Map<String, Map<String, Set<String>>>>();
    Map<ServiceComponentHost, State> directTransitionScHosts = new HashMap<ServiceComponentHost, State>();

    Resource.Type reqOpLvl = determineOperationLevel(requestProperties);

    String clusterName = requestProperties.get(RequestOperationLevel.OPERATION_CLUSTER_ID);
    if (clusterName != null && !clusterName.isEmpty()) {
      clusterNames.add(clusterName);
    }

    for (ServiceComponentHostRequest request : requests) {
      validateServiceComponentHostRequest(request);

      Cluster cluster = clusters.getCluster(request.getClusterName());

      if (StringUtils.isEmpty(request.getServiceName())) {
        request.setServiceName(getManagementController().findServiceName(cluster, request.getComponentName()));
      }

      ServiceComponent sc = getServiceComponent(
          request.getClusterName(), request.getServiceName(), request.getComponentName());

      logRequestInfo("Received a updateHostComponent request", request);

      if((clusterName == null || clusterName.isEmpty())
              && (request.getClusterName() != null
              && !request.getClusterName().isEmpty())) {
        clusterNames.add(request.getClusterName());
      }

      if (clusterNames.size() > 1) {
        throw new IllegalArgumentException("Updates to multiple clusters is not"
            + " supported");
      }

      // maps of cluster->services, services->components, components->hosts
      Map<String, Map<String, Set<String>>> clusterServices = requestClusters.get(request.getClusterName());
      if (clusterServices == null) {
        clusterServices = new HashMap<String, Map<String, Set<String>>>();
        requestClusters.put(request.getClusterName(), clusterServices);
      }

      Map<String, Set<String>> serviceComponents = clusterServices.get(request.getServiceName());
      if (serviceComponents == null) {
        serviceComponents = new HashMap<String, Set<String>>();
        clusterServices.put(request.getServiceName(), serviceComponents);
      }

      Set<String> componentHosts = serviceComponents.get(request.getComponentName());
      if (componentHosts == null) {
        componentHosts = new HashSet<String>();
        serviceComponents.put(request.getComponentName(), componentHosts) ;
      }

      if (componentHosts.contains(request.getHostname())) {
        throw new IllegalArgumentException("Invalid request contains duplicate hostcomponents");
      }

      componentHosts.add(request.getHostname());


      ServiceComponentHost sch = sc.getServiceComponentHost(request.getHostname());
      State oldState = sch.getState();
      State newState = null;
      if (request.getDesiredState() != null) {
        // set desired state on host component
        newState = State.valueOf(request.getDesiredState());

        // throw exception if desired state isn't a valid desired state (static check)
        if (!newState.isValidDesiredState()) {
          throw new IllegalArgumentException("Invalid arguments, invalid"
              + " desired state, desiredState=" + newState.toString());
        }
      }

      // Setting Maintenance state for host component
      if (null != request.getMaintenanceState()) {
        MaintenanceState newMaint = MaintenanceState.valueOf(request.getMaintenanceState());
        MaintenanceState oldMaint = maintenanceStateHelper.getEffectiveState(sch);

        if (newMaint != oldMaint) {
          if (sc.isClientComponent()) {
            throw new IllegalArgumentException("Invalid arguments, cannot set maintenance state on a client component");
          } else if (newMaint.equals(MaintenanceState.IMPLIED_FROM_HOST)  || newMaint.equals(MaintenanceState.IMPLIED_FROM_SERVICE)) {
            throw new IllegalArgumentException("Invalid arguments, can only set maintenance state to one of " +
                EnumSet.of(MaintenanceState.OFF, MaintenanceState.ON));
          } else {
            sch.setMaintenanceState(newMaint);
          }
        }
      }

      if (newState == null) {
        logComponentInfo("Nothing to do for new updateServiceComponentHost", request, oldState, null);
        continue;
      }

      // STARTED state is invalid for the client component, but this shouldn't cancel the whole stage
      if (sc.isClientComponent() && newState == State.STARTED &&
            !requestProperties.containsKey(sch.getServiceComponentName().toLowerCase())) {
        ignoredScHosts.add(sch);
        logComponentInfo("Ignoring ServiceComponentHost", request, sch.getState(), newState);
        continue;
      }

      if (sc.isClientComponent() &&
          !newState.isValidClientComponentState()) {
        throw new IllegalArgumentException("Invalid desired state for a client"
            + " component");
      }

      State oldSchState = sch.getState();
      // Client component reinstall allowed
      if (newState == oldSchState && !sc.isClientComponent() &&
          !requestProperties.containsKey(sch.getServiceComponentName().toLowerCase())) {

        ignoredScHosts.add(sch);
        logComponentInfo("Ignoring ServiceComponentHost", request, oldState, newState);
        continue;
      }

      if (! maintenanceStateHelper.isOperationAllowed(reqOpLvl, sch)) {
        ignoredScHosts.add(sch);
        logComponentInfo("Ignoring ServiceComponentHost", request, oldState, newState);
        continue;
      }

      if (! isValidStateTransition(stages, oldSchState, newState, sch)) {
        throw new AmbariException("Invalid state transition for host component"
            + ", clusterName=" + cluster.getClusterName()
            + ", clusterId=" + cluster.getClusterId()
            + ", serviceName=" + sch.getServiceName()
            + ", componentName=" + sch.getServiceComponentName()
            + ", hostname=" + sch.getHostName()
            + ", currentState=" + oldSchState
            + ", newDesiredState=" + newState);
      }

      if (isDirectTransition(oldSchState, newState)) {
        logComponentInfo("Handling direct transition update to host component", request, oldState, newState);
        directTransitionScHosts.put(sch, newState);
      } else {
        if (!changedScHosts.containsKey(sc.getName())) {
          changedScHosts.put(sc.getName(),
              new EnumMap<State, List<ServiceComponentHost>>(State.class));
        }
        if (!changedScHosts.get(sc.getName()).containsKey(newState)) {
          changedScHosts.get(sc.getName()).put(newState,
              new ArrayList<ServiceComponentHost>());
        }
        logComponentInfo("Handling update to host component", request, oldState, newState);
        changedScHosts.get(sc.getName()).get(newState).add(sch);
      }
    }

    doDirectTransitions(directTransitionScHosts);

    // just getting the first cluster
    Cluster cluster = clusters.getCluster(clusterNames.iterator().next());

    return getManagementController().addStages(
        stages, cluster, requestProperties, null, null, null,
        changedScHosts, ignoredScHosts, runSmokeTest, false);
  }