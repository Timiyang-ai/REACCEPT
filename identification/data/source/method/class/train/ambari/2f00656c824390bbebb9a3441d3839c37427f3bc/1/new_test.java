@Test
  public void testFindByClusterHostAndState() {
    Assert.assertEquals(1, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host1", RepositoryVersionState.CURRENT).size());
    Assert.assertEquals(0, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host1", RepositoryVersionState.INSTALLED).size());
    Assert.assertEquals(0, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host2", RepositoryVersionState.UPGRADING).size());
    Assert.assertEquals(0, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host3", RepositoryVersionState.UPGRADE_FAILED).size());

    addMoreVersions();

    Assert.assertEquals(2, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host1", RepositoryVersionState.INSTALLED).size());
    Assert.assertEquals(2, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host2", RepositoryVersionState.INSTALLED).size());
    Assert.assertEquals(2, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host3", RepositoryVersionState.INSTALLED).size());

    Assert.assertEquals(1, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host1", RepositoryVersionState.CURRENT).size());
    Assert.assertEquals(1, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host2", RepositoryVersionState.UPGRADING).size());
    Assert.assertEquals(1, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host3", RepositoryVersionState.UPGRADE_FAILED).size());
  }