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

    // no failues so far, check to see if any hosts are in MM so that this check
    // will produce a warning
    for (Host host : hosts) {
      MaintenanceState maintenanceState = host.getMaintenanceState(cluster.getClusterId());
      if (maintenanceState != MaintenanceState.OFF) {
        prerequisiteCheck.getFailedOn().add(host.getHostName());
      }
    }

    if (!prerequisiteCheck.getFailedOn().isEmpty()) {
      prerequisiteCheck.setStatus(PrereqCheckStatus.WARNING);
      prerequisiteCheck.setFailReason(
          getFailReason(KEY_HOSTS_IN_MM_WARNING, prerequisiteCheck, request));
    }
  }