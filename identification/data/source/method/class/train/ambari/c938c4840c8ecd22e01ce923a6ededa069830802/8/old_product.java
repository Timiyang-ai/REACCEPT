public synchronized void createHosts(Request request)
      throws AmbariException {

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
      if (! propertyMap.containsKey(HOSTGROUP_PROPERTY_ID)) {
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
          + ", hostnames=" + names.toString());
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
          "These hosts have not been registered with the server: " + names.toString());
    }

    Map<String, Set<String>> hostClustersMap = new HashMap<>();
    Map<String, Map<String, String>> hostAttributes = new HashMap<>();
    Set<String> allClusterSet = new HashSet<>();

    Map<String, TopologyCluster> addedTopologies = new HashMap<>();
    for (HostRequest hostRequest : hostRequests) {
      if (hostRequest.getHostname() != null &&
          !hostRequest.getHostname().isEmpty() &&
          hostRequest.getClusterName() != null &&
          !hostRequest.getClusterName().isEmpty()){

        Set<String> clusterSet = new HashSet<>();
        clusterSet.add(hostRequest.getClusterName());
        allClusterSet.add(hostRequest.getClusterName());
        hostClustersMap.put(hostRequest.getHostname(), clusterSet);
        if (hostRequest.getHostAttributes() != null) {
          hostAttributes.put(hostRequest.getHostname(), hostRequest.getHostAttributes());
        }
        String clusterId = Long.toString(clusters.getCluster(hostRequest.getClusterName()).getClusterId());
        if (!addedTopologies.containsKey(clusterId)) {
          addedTopologies.put(clusterId, new TopologyCluster());
        }
        Host addedHost = clusters.getHost(hostRequest.getHostname());
        addedTopologies.get(clusterId).addTopologyHost(new TopologyHost(addedHost.getHostId(),
            addedHost.getHostName(),
            addedHost.getRackInfo(),
            addedHost.getIPv4()));
      }
    }
    clusters.updateHostWithClusterAndAttributes(hostClustersMap, hostAttributes);

    for (String clusterName : allClusterSet) {
      clusters.getCluster(clusterName).recalculateAllClusterVersionStates();
    }
    TopologyUpdateEvent topologyUpdateEvent = new TopologyUpdateEvent(addedTopologies, TopologyUpdateEvent.EventType.ADD);
    stateUpdateEventPublisher.publish(topologyUpdateEvent);
  }