final Set<String> getServicesInUpgrade(PrereqCheckRequest request) throws AmbariException {
    final Cluster cluster = clustersProvider.get().getCluster(request.getClusterName());
    RepositoryVersionEntity repositoryVersion = request.getTargetRepositoryVersion();

    // the check is scoped to some services, so determine if any of those
    // services are included in this upgrade
    try {
      VersionDefinitionXml vdf = repositoryVersion.getRepositoryXml();
      ClusterVersionSummary clusterVersionSummary = vdf.getClusterSummary(cluster);
      return clusterVersionSummary.getAvailableServiceNames();
    } catch (Exception exception) {
      throw new AmbariException("Unable to run upgrade checks because of an invalid VDF",
          exception);
    }
  }