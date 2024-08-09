protected static Set<HostResponse> getHosts(AmbariManagementController controller, HostRequest request)
      throws AmbariException {

    //TODO/FIXME host can only belong to a single cluster so get host directly from Cluster
    //TODO/FIXME what is the requirement for filtering on host attributes?

    List<Host> hosts;
    Set<HostResponse> response = new HashSet<HostResponse>();
    Cluster           cluster  = null;

    Clusters                   clusters   = controller.getClusters();

    String clusterName = request.getClusterName();
    String hostName    = request.getHostname();

    if (clusterName != null) {
      //validate that cluster exists, throws exception if it doesn't.
      try {
        cluster = clusters.getCluster(clusterName);
      } catch (ObjectNotFoundException e) {
        throw new ParentObjectNotFoundException("Parent Cluster resource doesn't exist", e);
      }
    }

    if (hostName == null) {
      hosts = clusters.getHosts();
    } else {
      hosts = new ArrayList<Host>();
      try {
        hosts.add(clusters.getHost(request.getHostname()));
      } catch (HostNotFoundException e) {
        // add cluster name
        throw new HostNotFoundException(clusterName, hostName);
      }
    }

    // retrieve the cluster desired configs once instead of per host
    Map<String, DesiredConfig> desiredConfigs = null;
    if (null != cluster) {
      cluster.getDesiredConfigs();
    }

    for (Host h : hosts) {
      if (clusterName != null) {
        if (clusters.getClustersForHost(h.getHostName()).contains(cluster)) {
          HostResponse r = h.convertToResponse();

          r.setClusterName(clusterName);
          r.setDesiredHostConfigs(h.getDesiredHostConfigs(cluster, desiredConfigs));
          r.setMaintenanceState(h.getMaintenanceState(cluster.getClusterId()));

          response.add(r);
        } else if (hostName != null) {
          throw new HostNotFoundException(clusterName, hostName);
        }
      } else {
        HostResponse r = h.convertToResponse();

        Set<Cluster> clustersForHost = clusters.getClustersForHost(h.getHostName());
        //todo: host can only belong to a single cluster
        if (clustersForHost != null && clustersForHost.size() != 0) {
          Cluster clusterForHost = clustersForHost.iterator().next();
          r.setClusterName(clusterForHost.getClusterName());
          r.setDesiredHostConfigs(h.getDesiredHostConfigs(clusterForHost, desiredConfigs));
          r.setMaintenanceState(h.getMaintenanceState(clusterForHost.getClusterId()));
        }

        response.add(r);
      }
    }
    return response;
  }