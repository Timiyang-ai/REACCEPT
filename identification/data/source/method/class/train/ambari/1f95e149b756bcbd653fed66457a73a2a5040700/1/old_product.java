public boolean isApplicable(PrereqCheckRequest request, List<String> requiredServices, boolean requiredAll) throws AmbariException {
    final Cluster cluster = clustersProvider.get().getCluster(request.getClusterName());
    Set<String> services = cluster.getServices().keySet();

    // default return value depends on assign inside check block
    boolean serviceFound = requiredAll && !requiredServices.isEmpty();

    for (String service : requiredServices) {
      if ( services.contains(service) && !requiredAll) {
        serviceFound = true;
        break;
      } else if (!services.contains(service) && requiredAll) {
        serviceFound = false;
        break;
      }
    }

    return serviceFound;
  }