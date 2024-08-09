@Test
  public void testIsApplicable() throws Exception {
    final Cluster cluster = Mockito.mock(Cluster.class);
    Mockito.when(cluster.getClusterId()).thenReturn(1L);
    Mockito.when(m_clusters.getCluster("cluster")).thenReturn(cluster);
    Mockito.when(cluster.getCurrentStackVersion()).thenReturn(new StackId("HDP-2.3"));

    Map<String, Service> services = new HashMap<String, Service>();
    Mockito.when(cluster.getServices()).thenReturn(services);

    ClusterVersionEntity clusterVersionEntity = Mockito.mock(ClusterVersionEntity.class);
    Mockito.when(cluster.getCurrentClusterVersion()).thenReturn(clusterVersionEntity);

    PrereqCheckRequest request = new PrereqCheckRequest("cluster");
    request.setTargetStackId(new StackId("HDP", "2.3.1.1"));
    request.setSourceStackId(new StackId("HDP", "2.3.0.0"));

    // MAPREDUCE2 not installed
    Assert.assertFalse(m_check.isApplicable(request));

    // MAPREDUCE2 installed
    services.put("MAPREDUCE2", Mockito.mock(Service.class));
    Assert.assertTrue(m_check.isApplicable(request));
  }