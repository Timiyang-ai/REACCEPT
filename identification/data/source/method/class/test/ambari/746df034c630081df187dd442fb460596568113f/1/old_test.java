@Test
  public void testFindByClusterStackAndVersion() {
    Assert.assertEquals(3, hostVersionDAO.findByClusterStackAndVersion("test_cluster1", "HDP-2.2", "2.2.0.0-995").size());
    Assert.assertEquals(3, hostVersionDAO.findAll().size());

    addMoreVersions();

    Assert.assertEquals(3, hostVersionDAO.findByClusterStackAndVersion("test_cluster1", "HDP-2.2", "2.2.0.1-996").size());
    Assert.assertEquals(3, hostVersionDAO.findByClusterStackAndVersion("test_cluster1", "HDP-2.2", "2.2.1.0-500").size());
    Assert.assertEquals(9, hostVersionDAO.findAll().size());
  }