@Test
  public void testGetComponents___OR_Predicate_ServiceComponentNotFoundException() throws Exception {
    // member state mocks
    AmbariManagementController managementController = createMock(AmbariManagementController.class);
    Clusters clusters = createNiceMock(Clusters.class);
    AmbariMetaInfo ambariMetaInfo = createNiceMock(AmbariMetaInfo.class);
    StackId stackId = createNiceMock(StackId.class);

    Cluster cluster = createNiceMock(Cluster.class);
    Service service = createNiceMock(Service.class);
    ServiceComponent component1 = createNiceMock(ServiceComponent.class);
    ServiceComponent component2 = createNiceMock(ServiceComponent.class);
    ServiceComponentResponse response1 = createNiceMock(ServiceComponentResponse.class);
    ServiceComponentResponse response2 = createNiceMock(ServiceComponentResponse.class);

    // requests
    ServiceComponentRequest request1 = new ServiceComponentRequest("cluster1", "service1", "component1",
        null);
    ServiceComponentRequest request2 = new ServiceComponentRequest("cluster1", "service1", "component2",
        null);
    ServiceComponentRequest request3 = new ServiceComponentRequest("cluster1", "service1", "component3",
        null);
    ServiceComponentRequest request4 = new ServiceComponentRequest("cluster1", "service1", "component4",
        null);

    Set<ServiceComponentRequest> setRequests = new HashSet<ServiceComponentRequest>();
    setRequests.add(request1);
    setRequests.add(request2);
    setRequests.add(request3);
    setRequests.add(request4);

    // expectations
    // constructor init
    expect(managementController.getClusters()).andReturn(clusters).anyTimes();
    expect(managementController.getAmbariMetaInfo()).andReturn(ambariMetaInfo).anyTimes();

    // getComponents
    expect(clusters.getCluster("cluster1")).andReturn(cluster).times(4);
    expect(cluster.getDesiredStackVersion()).andReturn(stackId).anyTimes();
    expect(cluster.getService("service1")).andReturn(service).times(4);

    expect(service.getServiceComponent("component1")).andThrow(new ServiceComponentNotFoundException("cluster1", "service1", "component1"));
    expect(service.getServiceComponent("component2")).andThrow(new ServiceComponentNotFoundException("cluster1", "service1", "component2"));
    expect(service.getServiceComponent("component3")).andReturn(component1);
    expect(service.getServiceComponent("component4")).andReturn(component2);

    expect(component1.convertToResponse()).andReturn(response1);
    expect(component2.convertToResponse()).andReturn(response2);
    // replay mocks
    replay(clusters, cluster, service, component1,  component2, response1, response2, ambariMetaInfo, stackId, managementController);

    //test
    Set<ServiceComponentResponse> setResponses = getComponentResourceProvider(managementController).getComponents(setRequests);

    // assert and verify
    assertEquals(2, setResponses.size());
    assertTrue(setResponses.contains(response1));
    assertTrue(setResponses.contains(response2));

    verify(clusters, cluster, service, component1,  component2, response1, response2, ambariMetaInfo, stackId, managementController);
  }