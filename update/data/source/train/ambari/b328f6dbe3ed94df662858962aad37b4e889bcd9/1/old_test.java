@Test
  public void testGetComponents___ServiceComponentNotFoundException() throws Exception {
    // member state mocks
    Injector injector = createStrictMock(Injector.class);
    Capture<AmbariManagementController> controllerCapture = EasyMock.newCapture();
    Clusters clusters = createNiceMock(Clusters.class);
    MaintenanceStateHelper maintHelper = createNiceMock(MaintenanceStateHelper.class);
    Cluster cluster = createNiceMock(Cluster.class);
    Service service = createNiceMock(Service.class);

    // requests
    ServiceComponentRequest request1 = new ServiceComponentRequest("cluster1", "service1", "component1",
        null);

    Set<ServiceComponentRequest> setRequests = new HashSet<ServiceComponentRequest>();
    setRequests.add(request1);

    // expectations
    // constructor init
    injector.injectMembers(capture(controllerCapture));
    expect(injector.getInstance(Gson.class)).andReturn(null);
    expect(injector.getInstance(MaintenanceStateHelper.class)).andReturn(maintHelper);
    expect(injector.getInstance(KerberosHelper.class)).andReturn(createNiceMock(KerberosHelper.class));

    // getComponents
    expect(clusters.getCluster("cluster1")).andReturn(cluster);
    expect(cluster.getService("service1")).andReturn(service);
    expect(service.getServiceComponent("component1")).andThrow(
        new ServiceComponentNotFoundException("cluster1", "service1", "component1"));
    // replay mocks
    replay(maintHelper, injector, clusters, cluster, service);

    //test
    AmbariManagementController controller = new AmbariManagementControllerImpl(null, clusters, injector);

    // assert that exception is thrown in case where there is a single request
    try {
      getComponentResourceProvider(controller).getComponents(setRequests);
      fail("expected ServiceComponentNotFoundException");
    } catch (ServiceComponentNotFoundException e) {
      // expected
    }

    assertSame(controller, controllerCapture.getValue());
    verify(injector, clusters, cluster, service);
  }