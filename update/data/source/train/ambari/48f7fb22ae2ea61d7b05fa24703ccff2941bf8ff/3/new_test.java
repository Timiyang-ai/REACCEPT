@Test(expected = HostNotFoundException.class)
  public void testGetHosts___HostNotFoundException() throws Exception {
    // member state mocks
    Injector injector = createInjector();

    AmbariManagementController managementController = injector.getInstance(AmbariManagementController.class);
    Clusters clusters = injector.getInstance(Clusters.class);
    Cluster cluster = createMock(Cluster.class);

    // requests
    HostRequest request1 = new HostRequest("host1", "cluster1");
    Set<HostRequest> setRequests = Collections.singleton(request1);

    // expectations
    expect(managementController.getClusters()).andReturn(clusters).anyTimes();
    expect(clusters.getCluster("cluster1")).andReturn(cluster);
    expect(clusters.getHost("host1")).andThrow(new HostNotFoundException("host1"));

    // replay mocks
    replayAll();

    SecurityContextHolder.getContext().setAuthentication(TestAuthenticationFactory.createAdministrator());
    getHosts(managementController, setRequests);

    verifyAll();
  }