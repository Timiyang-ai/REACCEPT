protected synchronized RequestStatusResponse updateComponents(Set<ServiceComponentRequest> requests,
                                                             Map<String, String> requestProperties, boolean runSmokeTest)
      throws AmbariException {

    if (requests.isEmpty()) {
      LOG.warn("Received an empty requests set");
      return null;
    }

    Clusters clusters = getManagementController().getClusters();
    AmbariMetaInfo ambariMetaInfo = getManagementController().getAmbariMetaInfo();

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

    for (ServiceComponentRequest request : requests) {
      if (request.getClusterName() == null
          || request.getClusterName().isEmpty()
          || request.getComponentName() == null
          || request.getComponentName().isEmpty()) {
        throw new IllegalArgumentException("Invalid arguments, cluster name"
            + ", service name and component name should be provided to"
            + " update components");
      }

      LOG.info("Received a updateComponent request"
          + ", clusterName=" + request.getClusterName()
          + ", serviceName=" + request.getServiceName()
          + ", componentName=" + request.getComponentName()
          + ", request=" + request.toString());

      Cluster cluster = clusters.getCluster(request.getClusterName());

      if (request.getServiceName() == null
          || request.getServiceName().isEmpty()) {
        StackId stackId = cluster.getDesiredStackVersion();
        String serviceName =
            ambariMetaInfo.getComponentToService(stackId.getStackName(),
                stackId.getStackVersion(), request.getComponentName());
        if (LOG.isDebugEnabled()) {
          LOG.debug("Looking up service name for component"
              + ", componentName=" + request.getComponentName()
              + ", serviceName=" + serviceName);
        }

        if (serviceName == null
            || serviceName.isEmpty()) {
          throw new AmbariException("Could not find service for component"
              + ", componentName=" + request.getComponentName()
              + ", clusterName=" + cluster.getClusterName()
              + ", stackInfo=" + stackId.getStackId());
        }
        request.setServiceName(serviceName);
      }

      if (LOG.isDebugEnabled()) {
        LOG.debug("Received a updateComponent request"
            + ", clusterName=" + request.getClusterName()
            + ", serviceName=" + request.getServiceName()
            + ", componentName=" + request.getComponentName()
            + ", request=" + request);
      }

      clusterNames.add(request.getClusterName());

      if (clusterNames.size() > 1) {
        // FIXME throw correct error
        throw new IllegalArgumentException("Updates to multiple clusters is not"
            + " supported");
      }

      if (!componentNames.containsKey(request.getClusterName())) {
        componentNames.put(request.getClusterName(),
            new HashMap<String, Set<String>>());
      }
      if (!componentNames.get(request.getClusterName())
          .containsKey(request.getServiceName())) {
        componentNames.get(request.getClusterName()).put(
            request.getServiceName(), new HashSet<String>());
      }
      if (componentNames.get(request.getClusterName())
          .get(request.getServiceName()).contains(request.getComponentName())){
        // throw error later for dup
        throw new IllegalArgumentException("Invalid request contains duplicate"
            + " service components");
      }
      componentNames.get(request.getClusterName())
          .get(request.getServiceName()).add(request.getComponentName());

      Service s = cluster.getService(request.getServiceName());
      ServiceComponent sc = s.getServiceComponent(
          request.getComponentName());
      State newState = null;
      if (request.getDesiredState() != null) {
        newState = State.valueOf(request.getDesiredState());
        if (!newState.isValidDesiredState()) {
          throw new IllegalArgumentException("Invalid arguments, invalid"
              + " desired state, desiredState=" + newState.toString());
        }
      }

      if (request.getConfigVersions() != null) {
        State.checkUpdateConfiguration(sc, newState);

        for (Map.Entry<String,String> entry :
            request.getConfigVersions().entrySet()) {
          Config config = cluster.getConfig(
              entry.getKey(), entry.getValue());
          if (null == config) {
            // throw error for invalid config
            throw new AmbariException("Trying to update servicecomponent with"
                + " invalid configs"
                + ", clusterName=" + cluster.getClusterName()
                + ", clusterId=" + cluster.getClusterId()
                + ", serviceName=" + s.getName()
                + ", componentName=" + sc.getName()
                + ", invalidConfigType=" + entry.getKey()
                + ", invalidConfigTag=" + entry.getValue());
          }
        }
      }

      if (newState == null) {
        if (LOG.isDebugEnabled()) {
          LOG.debug("Nothing to do for new updateServiceComponent request"
              + ", clusterName=" + request.getClusterName()
              + ", serviceName=" + request.getServiceName()
              + ", componentName=" + request.getComponentName()
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
              + ", clusterName=" + request.getClusterName()
              + ", serviceName=" + s.getName()
              + ", componentName=" + sc.getName()
              + ", currentDesiredState=" + oldScState
              + ", newDesiredState=" + newState);
        }
        changedComps.get(newState).add(sc);
      }

      for (ServiceComponentHost sch : sc.getServiceComponentHosts().values()) {
        State oldSchState = sch.getState();
        if (oldSchState == State.MAINTENANCE || oldSchState == State.UNKNOWN) {
          if (LOG.isDebugEnabled()) {
            LOG.debug("Ignoring ServiceComponentHost"
                + ", clusterName=" + request.getClusterName()
                + ", serviceName=" + s.getName()
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
                + ", clusterName=" + request.getClusterName()
                + ", serviceName=" + s.getName()
                + ", componentName=" + sc.getName()
                + ", hostname=" + sch.getHostName()
                + ", currentState=" + oldSchState
                + ", newDesiredState=" + newState);
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
              + ", clusterName=" + request.getClusterName()
              + ", serviceName=" + s.getName()
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

    // TODO if all components reach a common state, should service state be
    // modified?

    for (ServiceComponentRequest request : requests) {
      Cluster cluster = clusters.getCluster(request.getClusterName());
      Service s = cluster.getService(request.getServiceName());
      ServiceComponent sc = s.getServiceComponent(
          request.getComponentName());
      if (request.getConfigVersions() != null) {
        Map<String, Config> updated = new HashMap<String, Config>();

        for (Map.Entry<String,String> entry :
            request.getConfigVersions().entrySet()) {
          Config config = cluster.getConfig(
              entry.getKey(), entry.getValue());
          updated.put(config.getType(), config);
        }

        if (!updated.isEmpty()) {
          sc.updateDesiredConfigs(updated);
          for (ServiceComponentHost sch :
              sc.getServiceComponentHosts().values()) {
            sch.deleteDesiredConfigs(updated.keySet());
            sch.persist();
          }
          sc.persist();
        }
      }
    }

    Cluster cluster = clusters.getCluster(clusterNames.iterator().next());

    return getManagementController().createStages(cluster, requestProperties, null, null, changedComps, changedScHosts,
        ignoredScHosts, runSmokeTest, false);
  }