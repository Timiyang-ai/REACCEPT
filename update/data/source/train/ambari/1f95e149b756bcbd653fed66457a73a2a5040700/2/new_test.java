@Test
  public void testIsApplicable() throws Exception {
    final Cluster cluster = Mockito.mock(Cluster.class);
    Mockito.when(cluster.getClusterId()).thenReturn(1L);
    Mockito.when(m_clusters.getCluster("cluster")).thenReturn(cluster);
    Mockito.when(cluster.getCurrentStackVersion()).thenReturn(new StackId("HDP-2.3"));

    Mockito.when(cluster.getServices()).thenReturn(m_services);

    PrereqCheckRequest request = new PrereqCheckRequest("cluster");
    request.setSourceStackId(new StackId("HDP", "2.3.0.0"));
    request.setTargetRepositoryVersion(m_repositoryVersion);

    // MAPREDUCE2 not installed
    Assert.assertFalse(m_check.isApplicable(request));

    // MAPREDUCE2 installed
    m_services.put("MAPREDUCE2", Mockito.mock(Service.class));
    Assert.assertTrue(m_check.isApplicable(request));
  }