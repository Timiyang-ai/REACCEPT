  private void createCluster(String clusterName) throws Exception{
    RepositoryVersionDAO repoDAO = injector.getInstance(RepositoryVersionDAO.class);
    ClusterRequest r = new ClusterRequest(null, clusterName, State.INSTALLED.name(), SecurityType.NONE, "HDP-0.1", null);
    controller.createCluster(r);
  }