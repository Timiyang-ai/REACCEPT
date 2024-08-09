@Override
  public void perform(PrerequisiteCheck prerequisiteCheck, PrereqCheckRequest request)
      throws AmbariException {
    final String clusterName = request.getClusterName();
    final Cluster cluster = clustersProvider.get().getCluster(clusterName);
    Collection<Host> hosts = cluster.getHosts();

    for (Host host : hosts) {
      HealthStatus hostHealth = host.getHealthStatus().getHealthStatus();
      MaintenanceState maintenanceState = host.getMaintenanceState(cluster.getClusterId());
      switch (hostHealth) {
        case UNHEALTHY:
        case UNKNOWN:
          if (maintenanceState == MaintenanceState.OFF) {
            prerequisiteCheck.getFailedOn().add(host.getHostName());
          }
          break;
        default:
          break;

      }
    }

    // for any hosts unhealthy and NOT in MM mode, fail this check
    if (!prerequisiteCheck.getFailedOn().isEmpty()) {
      prerequisiteCheck.setStatus(PrereqCheckStatus.FAIL);
      prerequisiteCheck.setFailReason(getFailReason(prerequisiteCheck, request));
      return;
    }
  }