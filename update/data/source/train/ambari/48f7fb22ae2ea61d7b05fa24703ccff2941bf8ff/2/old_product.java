@Subscribe
  @Transactional
  public void onHostEvent(HostsRemovedEvent event) {
    if (LOG.isDebugEnabled()) {
      LOG.debug(event.toString());
    }

    try {
      Set<Cluster> clusters = event.getClusters();
      for (Cluster cluster : clusters) {
        Collection<ClusterVersionEntity> allClusterVersions = cluster.getAllClusterVersions();

        for (ClusterVersionEntity clusterVersion : allClusterVersions) {
          RepositoryVersionState repositoryVersionState = clusterVersion.getState();

          // the CURRENT/INSTALLED states should not be affected by a host
          // removal - if it's already current then removing a host will never
          // make it not CURRENT or not INSTALLED
          switch (repositoryVersionState) {
            case CURRENT:
            case INSTALLED:
              continue;
            default:
              break;
          }

          RepositoryVersionEntity repositoryVersion = clusterVersion.getRepositoryVersion();
          cluster.recalculateClusterVersionState(repositoryVersion);
        }
      }

    } catch (AmbariException ambariException) {
      LOG.error(
          "Unable to recalculate the cluster repository version state when a host was removed",
          ambariException);
    }
  }