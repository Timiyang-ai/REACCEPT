@Test
  public void testIsApplicable() throws Exception {
    final Cluster cluster = Mockito.mock(Cluster.class);
    Mockito.when(cluster.getClusterId()).thenReturn(1L);
    Mockito.when(m_clusters.getCluster("cluster")).thenReturn(cluster);
    Mockito.when(cluster.getCurrentStackVersion()).thenReturn(new StackId("HDP-2.2"));

    Map<String, Service> services = new HashMap<String, Service>();
    Mockito.when(cluster.getServices()).thenReturn(services);

    ClusterVersionEntity clusterVersionEntity = Mockito.mock(ClusterVersionEntity.class);
    Mockito.when(cluster.getCurrentClusterVersion()).thenReturn(clusterVersionEntity);

    RepositoryVersionEntity repositoryVersionEntity = Mockito.mock(RepositoryVersionEntity.class);
    Mockito.when(clusterVersionEntity.getRepositoryVersion()).thenReturn(repositoryVersionEntity);
    Mockito.when(repositoryVersionEntity.getVersion()).thenReturn("2.2.4.2");

    PrereqCheckRequest request = new PrereqCheckRequest("cluster");
    request.setRepositoryVersion("2.3.0.0");

    // YARN not installed
    Assert.assertFalse(m_check.isApplicable(request));

    // YARN installed
    services.put("YARN", Mockito.mock(Service.class));
    Assert.assertTrue(m_check.isApplicable(request));

    Mockito.when(repositoryVersionEntity.getVersion()).thenReturn("2.2.0.0");
    Assert.assertFalse(m_check.isApplicable(request));

    Mockito.when(repositoryVersionEntity.getVersion()).thenReturn("2.2.4.2");
    Assert.assertTrue(m_check.isApplicable(request));
  }