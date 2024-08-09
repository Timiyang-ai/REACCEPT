public synchronized void createHosts(Request request)
      throws AmbariException, AuthorizationException {

    Set<Map<String, Object>> propertySet = request.getProperties();
    if (propertySet == null || propertySet.isEmpty()) {
      LOG.warn("Received a create host request with no associated property sets");
      return;
    }

    AmbariManagementController controller = getManagementController();
    Clusters                   clusters   = controller.getClusters();

    Set<String> duplicates = new HashSet<>();
    Set<String> unknowns = new HashSet<>();
    Set<String> allHosts = new HashSet<>();


    Set<HostRequest> hostRequests = new HashSet<>();
    for (Map<String, Object> propertyMap : propertySet) {
      HostRequest hostRequest = getRequest(propertyMap);
      hostRequests.add(hostRequest);
      if (! propertyMap.containsKey(HOST_GROUP_PROPERTY_ID)) {
        createHostResource(clusters, duplicates, unknowns, allHosts, hostRequest);
      }
    }

    if (!duplicates.isEmpty()) {
      StringBuilder names = new StringBuilder();
      boolean first = true;
      for (String hName : duplicates) {
        if (!first) {
          names.append(",");
        }
        first = false;
        names.append(hName);
      }
      throw new IllegalArgumentException("Invalid request contains"
          + " duplicate hostnames"
          + ", hostnames=" + names);
    }

    if (!unknowns.isEmpty()) {
      StringBuilder names = new StringBuilder();
      boolean first = true;
      for (String hName : unknowns) {
        if (!first) {
          names.append(",");
        }
        first = false;
        names.append(hName);
      }

      throw new IllegalArgumentException("Attempted to add unknown hosts to a cluster.  " +
          "These hosts have not been registered with the server: " + names);
    }

    Map<String, Set<String>> hostClustersMap = new HashMap<>();
    Map<String, Map<String, String>> hostAttributes = new HashMap<>();
    Set<String> allClusterSet = new HashSet<>();

    TreeMap<String, TopologyCluster> addedTopologies = new TreeMap<>();
    List<HostLevelParamsUpdateEvent> hostLevelParamsUpdateEvents = new ArrayList<>();
    for (HostRequest hostRequest : hostRequests) {
      if (hostRequest.getHostname() != null &&
          !hostRequest.getHostname().isEmpty() &&
          hostRequest.getClusterName() != null &&
          !hostRequest.getClusterName().isEmpty()){

        Set<String> clusterSet = new HashSet<>();
        clusterSet.add(hostRequest.getClusterName());
        allClusterSet.add(hostRequest.getClusterName());
        hostClustersMap.put(hostRequest.getHostname(), clusterSet);
        Cluster cl = clusters.getCluster(hostRequest.getClusterName());
        String clusterId = Long.toString(cl.getClusterId());
        if (!addedTopologies.containsKey(clusterId)) {
          addedTopologies.put(clusterId, new TopologyCluster());
        }
        Host addedHost = clusters.getHost(hostRequest.getHostname());
        addedTopologies.get(clusterId).addTopologyHost(new TopologyHost(addedHost.getHostId(),
            addedHost.getHostName(),
            addedHost.getRackInfo(),
            addedHost.getIPv4()));
        //TODO fix repo info param
        HostLevelParamsUpdateEvent hostLevelParamsUpdateEvent = new HostLevelParamsUpdateEvent(clusterId, new HostLevelParamsCluster(
            null,//ambariMetaInfo.getRepoInfo(cl, addedHost),
            recoveryConfigHelper.getRecoveryConfig(clusters.getCluster(hostRequest.getClusterName()).getClusterName(),
                addedHost.getHostName())
        ));
        hostLevelParamsUpdateEvent.setHostName(addedHost.getHostName());
        hostLevelParamsUpdateEvents.add(hostLevelParamsUpdateEvent);
      }
    }
    clusters.updateHostWithClusterAndAttributes(hostClustersMap, hostAttributes);

    // TODO add rack change to topology update
    updateHostRackInfoIfChanged(clusters, hostRequests);

    TopologyUpdateEvent topologyUpdateEvent =
        new TopologyUpdateEvent(addedTopologies, TopologyUpdateEvent.EventType.UPDATE);
    topologyHolder.updateData(topologyUpdateEvent);
    for (HostLevelParamsUpdateEvent hostLevelParamsUpdateEvent : hostLevelParamsUpdateEvents) {
      hostLevelParamsHolder.updateData(hostLevelParamsUpdateEvent);
    }
  }