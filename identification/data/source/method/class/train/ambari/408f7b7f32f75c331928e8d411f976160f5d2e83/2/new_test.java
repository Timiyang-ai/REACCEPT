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

    Map<String, String> checkProperties = new HashMap<String, String>();
    checkProperties.put("min-applicable-stack-version","HDP-2.2.4.2");
    PrerequisiteCheckConfig prerequisiteCheckConfig = Mockito.mock(PrerequisiteCheckConfig.class);
    Mockito.when(prerequisiteCheckConfig.getCheckProperties(
        m_check.getClass().getName())).thenReturn(checkProperties);

    PrereqCheckRequest request = new PrereqCheckRequest("cluster");
    request.setRepositoryVersion("2.3.0.0");
    request.setPrerequisiteCheckConfig(prerequisiteCheckConfig);

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