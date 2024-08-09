@Override
  public boolean isApplicable(PrereqCheckRequest request) throws AmbariException {
    if (!super.isApplicable(request)) {
      return false;
    }

    final Cluster cluster = clustersProvider.get().getCluster(request.getClusterName());
    Map<String, Service> services = cluster.getServices();
    if (!services.containsKey("YARN")) {
      return false;
    }

    // not applicable if not HDP 2.2.4.2 or later
    String stackName = cluster.getCurrentStackVersion().getStackName();
    if (!"HDP".equals(stackName)) {
      return false;
    }

    String currentClusterRepositoryVersion = cluster.getCurrentClusterVersion().getRepositoryVersion().getVersion();
    if (VersionUtils.compareVersions(currentClusterRepositoryVersion, "2.2.4.2") < 0) {
      return false;
    }

    return true;
  }