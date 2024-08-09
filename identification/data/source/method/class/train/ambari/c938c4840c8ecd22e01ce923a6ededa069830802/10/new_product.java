protected synchronized void createHosts(Request request)
      throws AmbariException {

    Set<Map<String, Object>> propertySet = request.getProperties();
    if (propertySet == null || propertySet.isEmpty()) {
      LOG.warn("Received a create host request with no associated property sets");
      return;
    }

    AmbariManagementController controller = getManagementController();
    Clusters                   clusters   = controller.getClusters();

    Set<String> duplicates = new HashSet<String>();
    Set<String> unknowns = new HashSet<String>();
    Set<String> allHosts = new HashSet<String>();


    Set<HostRequest> hostRequests = new HashSet<HostRequest>();
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

    Map<String, Set<String>> hostClustersMap = new HashMap<String, Set<String>>();
    Map<String, Map<String, String>> hostAttributes = new HashMap<String, Map<String, String>>();
    Set<String> allClusterSet = new HashSet<String>();

    for (HostRequest hostRequest : hostRequests) {
      if (hostRequest.getHostname() != null &&
          !hostRequest.getHostname().isEmpty() &&
          hostRequest.getClusterName() != null &&
          !hostRequest.getClusterName().isEmpty()){

        Set<String> clusterSet = new HashSet<String>();
        clusterSet.add(hostRequest.getClusterName());
        allClusterSet.add(hostRequest.getClusterName());
        hostClustersMap.put(hostRequest.getHostname(), clusterSet);
        if (hostRequest.getHostAttributes() != null) {
          hostAttributes.put(hostRequest.getHostname(), hostRequest.getHostAttributes());
        }
      }
    }
    clusters.updateHostWithClusterAndAttributes(hostClustersMap, hostAttributes);

    for (String clusterName : allClusterSet) {
      clusters.getCluster(clusterName).recalculateAllClusterVersionStates();
    }
  }