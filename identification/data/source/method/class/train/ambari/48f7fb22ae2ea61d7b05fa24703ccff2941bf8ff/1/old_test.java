@Test
  public void testGetHosts___OR_Predicate_HostNotFoundException() throws Exception {
    // member state mocks
    Injector injector = createInjector();

    AmbariManagementController managementController = injector.getInstance(AmbariManagementController.class);
    Clusters clusters = injector.getInstance(Clusters.class);
    Cluster cluster = createMock(Cluster.class);
    Host host1 = createNiceMock(Host.class);
    Host host2 = createNiceMock(Host.class);
    HostResponse response = createNiceMock(HostResponse.class);
    HostResponse response2 = createNiceMock(HostResponse.class);

    // requests
    HostRequest request1 = new HostRequest("host1", "cluster1", Collections.<String, String>emptyMap());
    HostRequest request2 = new HostRequest("host2", "cluster1", Collections.<String, String>emptyMap());
    HostRequest request3 = new HostRequest("host3", "cluster1", Collections.<String, String>emptyMap());
    HostRequest request4 = new HostRequest("host4", "cluster1", Collections.<String, String>emptyMap());

    Set<HostRequest> setRequests = new HashSet<HostRequest>();
    setRequests.add(request1);
    setRequests.add(request2);
    setRequests.add(request3);
    setRequests.add(request4);

    // expectations

    expect(managementController.getClusters()).andReturn(clusters).anyTimes();

    expect(clusters.getCluster("cluster1")).andReturn(cluster).times(4);

    expect(clusters.getHost("host1")).andReturn(host1);
    expect(host1.getHostName()).andReturn("host1").anyTimes();
    expect(clusters.getClustersForHost("host1")).andReturn(Collections.singleton(cluster));
    expect(host1.convertToResponse()).andReturn(response);
    response.setClusterName("cluster1");

    expect(clusters.getHost("host2")).andReturn(host2);
    expect(host2.getHostName()).andReturn("host2").anyTimes();
    expect(clusters.getClustersForHost("host2")).andReturn(Collections.singleton(cluster));
    expect(host2.convertToResponse()).andReturn(response2);
    response2.setClusterName("cluster1");

    expect(clusters.getHost("host3")).andThrow(new HostNotFoundException("host3"));
    expect(clusters.getHost("host4")).andThrow(new HostNotFoundException("host4"));

    expect(cluster.getClusterId()).andReturn(2L).anyTimes();
    expect(cluster.getDesiredConfigs()).andReturn(new HashMap<String, DesiredConfig>()).anyTimes();

    // replay mocks
    replayAll();

    SecurityContextHolder.getContext().setAuthentication(TestAuthenticationFactory.createAdministrator());

    //test
    Set<HostResponse> setResponses = getHosts(managementController, setRequests);

    // assert and verify
    assertEquals(2, setResponses.size());
    assertTrue(setResponses.contains(response));
    assertTrue(setResponses.contains(response2));

    verifyAll();
  }