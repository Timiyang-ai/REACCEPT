@Test
  public void testPerform() throws Exception {
    final Cluster cluster = Mockito.mock(Cluster.class);
    Service hive = Mockito.mock(Service.class);
    ServiceComponent metastore = Mockito.mock(ServiceComponent.class);

    Mockito.when(cluster.getClusterId()).thenReturn(1L);
    Mockito.when(m_clusters.getCluster("cluster")).thenReturn(cluster);
    Mockito.when(cluster.getService("HIVE")).thenReturn(hive);

    Mockito.when(hive.getServiceComponent("HIVE_METASTORE")).thenReturn(metastore);

    Map<String, ServiceComponentHost> metastores = new HashMap<>();
    Mockito.when(metastore.getServiceComponentHosts()).thenReturn(metastores);

    PrerequisiteCheck check = new PrerequisiteCheck(null, null);
    PrereqCheckRequest request = new PrereqCheckRequest("cluster");
    request.setTargetRepositoryVersion(m_repositoryVersion);
    m_check.perform(check, request);

    Assert.assertEquals(PrereqCheckStatus.WARNING, check.getStatus());

    metastores.put("c6401", Mockito.mock(ServiceComponentHost.class));
    metastores.put("c6402", Mockito.mock(ServiceComponentHost.class));

    check = new PrerequisiteCheck(null, null);
    m_check.perform(check, request);

    Assert.assertEquals(PrereqCheckStatus.PASS, check.getStatus());
  }