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

    // Applicable only if stack not defined in MinimumApplicableStackVersion, or
    // version equals or exceeds the enumerated version.
    for (MinimumApplicableStackVersion minimumStackVersion : MinimumApplicableStackVersion.values()) {
      String stackName = cluster.getCurrentStackVersion().getStackName();
      if (minimumStackVersion.getStackName().equals(stackName)){
        String currentClusterRepositoryVersion = cluster.getCurrentClusterVersion().getRepositoryVersion().getVersion();
        return VersionUtils.compareVersions(currentClusterRepositoryVersion, minimumStackVersion.getStackVersion()) >= 0;
      }
    }

    return true;
  }