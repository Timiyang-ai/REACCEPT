@Test
  public void testFindByClusterStackVersionAndHost() {
    HostVersionEntity hostVersionEntity1 = new HostVersionEntity("test_host1", helper.getOrCreateRepositoryVersion("HDP-2.2", "2.2.0.0-995"), RepositoryVersionState.CURRENT);
    hostVersionEntity1.setId(1L);
    hostVersionEntity1.setHostEntity(hostDAO.findByName("test_host1"));
    HostVersionEntity hostVersionEntity2 = new HostVersionEntity("test_host2", helper.getOrCreateRepositoryVersion("HDP-2.2", "2.2.0.0-995"), RepositoryVersionState.INSTALLED);
    hostVersionEntity2.setId(2L);
    hostVersionEntity2.setHostEntity(hostDAO.findByName("test_host2"));
    HostVersionEntity hostVersionEntity3 = new HostVersionEntity("test_host3", helper.getOrCreateRepositoryVersion("HDP-2.2", "2.2.0.0-995"), RepositoryVersionState.INSTALLED);
    hostVersionEntity3.setId(3L);
    hostVersionEntity3.setHostEntity(hostDAO.findByName("test_host3"));

    Assert.assertEquals(hostVersionEntity1, hostVersionDAO.findByClusterStackVersionAndHost("test_cluster1", "HDP-2.2", "2.2.0.0-995", "test_host1"));
    Assert.assertEquals(hostVersionEntity2, hostVersionDAO.findByClusterStackVersionAndHost("test_cluster1", "HDP-2.2", "2.2.0.0-995", "test_host2"));
    Assert.assertEquals(hostVersionEntity3, hostVersionDAO.findByClusterStackVersionAndHost("test_cluster1", "HDP-2.2", "2.2.0.0-995", "test_host3"));

    // Test non-existent objects
    Assert.assertEquals(null, hostVersionDAO.findByClusterStackVersionAndHost("non_existent_cluster", "HDP-2.2", "2.2.0.0-995", "test_host3"));
    Assert.assertEquals(null, hostVersionDAO.findByClusterStackVersionAndHost("test_cluster1", "non_existent_stack", "2.2.0.0-995", "test_host3"));
    Assert.assertEquals(null, hostVersionDAO.findByClusterStackVersionAndHost("test_cluster1", "HDP-2.2", "non_existent_version", "test_host3"));
    Assert.assertEquals(null, hostVersionDAO.findByClusterStackVersionAndHost("test_cluster1", "HDP-2.2", "non_existent_version", "non_existent_host"));

    addMoreVersions();

    // Expected
    HostVersionEntity hostVersionEntity1LastExpected = new HostVersionEntity("test_host1", helper.getOrCreateRepositoryVersion("HDP-2.2", "2.2.1.0-500"), RepositoryVersionState.INSTALLED);
    HostVersionEntity hostVersionEntity2LastExpected = new HostVersionEntity("test_host2", helper.getOrCreateRepositoryVersion("HDP-2.2", "2.2.1.0-500"), RepositoryVersionState.UPGRADING);
    HostVersionEntity hostVersionEntity3LastExpected = new HostVersionEntity("test_host3", helper.getOrCreateRepositoryVersion("HDP-2.2", "2.2.1.0-500"), RepositoryVersionState.UPGRADE_FAILED);

    // Actual
    HostVersionEntity hostVersionEntity1LastActual = hostVersionDAO.findByClusterStackVersionAndHost("test_cluster1", "HDP-2.2", "2.2.1.0-500", "test_host1");
    HostVersionEntity hostVersionEntity2LastActual = hostVersionDAO.findByClusterStackVersionAndHost("test_cluster1", "HDP-2.2", "2.2.1.0-500", "test_host2");
    HostVersionEntity hostVersionEntity3LastActual = hostVersionDAO.findByClusterStackVersionAndHost("test_cluster1", "HDP-2.2", "2.2.1.0-500", "test_host3");

    // Trying to Mock the actual objects to override the getId() method will not work because the class that mockito creates
    // is still a Mockito wrapper. Instead, take advantage of an overloaded constructor that ignores the Id.
    Assert.assertEquals(hostVersionEntity1LastExpected, new HostVersionEntity(hostVersionEntity1LastActual));
    Assert.assertEquals(hostVersionEntity2LastExpected, new HostVersionEntity(hostVersionEntity2LastActual));
    Assert.assertEquals(hostVersionEntity3LastExpected, new HostVersionEntity(hostVersionEntity3LastActual));
  }