@Override
  public boolean isApplicable(PrereqCheckRequest request) throws AmbariException {
    if (!super.isApplicable(request, Arrays.asList("MAPREDUCE2"), true)) {
      return false;
    }

    final Cluster cluster = clustersProvider.get().getCluster(request.getClusterName());

    // Applicable only if stack not defined in MinimumApplicableStackVersion, or
    // version equals or exceeds the enumerated version.
    for (MinimumApplicableStackVersion minimumStackVersion : MinimumApplicableStackVersion.values()) {
      String stackName = cluster.getCurrentStackVersion().getStackName();
      if (minimumStackVersion.getStackName().equals(stackName)){
        String targetVersion = request.getTargetStackId().getStackVersion();
        String sourceVersion = request.getSourceStackId().getStackVersion();
        return VersionUtils.compareVersions(targetVersion, minimumStackVersion.getStackVersion()) >= 0 &&
               VersionUtils.compareVersions(sourceVersion, minimumStackVersion.getStackVersion()) >= 0;
      }
    }

    return true;
  }