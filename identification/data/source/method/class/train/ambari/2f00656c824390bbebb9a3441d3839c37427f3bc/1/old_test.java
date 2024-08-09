@Test
  public void testFindByClusterHostAndState() {
    Assert.assertEquals(1, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host1", UpgradeState.NONE).size());
    Assert.assertEquals(0, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host1", UpgradeState.PENDING).size());
    Assert.assertEquals(0, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host2", UpgradeState.IN_PROGRESS).size());
    Assert.assertEquals(0, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host3", UpgradeState.FAILED).size());

    addMoreVersions();

    Assert.assertEquals(2, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host1", UpgradeState.NONE).size());
    Assert.assertEquals(2, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host2", UpgradeState.NONE).size());
    Assert.assertEquals(2, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host3", UpgradeState.NONE).size());

    Assert.assertEquals(1, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host1", UpgradeState.PENDING).size());
    Assert.assertEquals(1, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host2", UpgradeState.IN_PROGRESS).size());
    Assert.assertEquals(1, hostVersionDAO.findByClusterHostAndState("test_cluster1", "test_host3", UpgradeState.FAILED).size());
  }