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

    if(null == _configuration)
      _configuration = new Configuration();
    
    String rollingUpgradeStack = _configuration.getRollingUpgradeStack(); 
    // not applicable if not HDP 2.2.4.2 or later    
    String stackName = cluster.getCurrentStackVersion().getStackName();
    if (!rollingUpgradeStack.equals(stackName)) {
      return false;
    }

    String rollingUpgradeVersion = _configuration.getRollingUpgradeVersion();
    String currentClusterRepositoryVersion = cluster.getCurrentClusterVersion().getRepositoryVersion().getVersion();
    if (VersionUtils.compareVersions(currentClusterRepositoryVersion, rollingUpgradeVersion) < 0) {
      return false;
    }

    return true;
  }