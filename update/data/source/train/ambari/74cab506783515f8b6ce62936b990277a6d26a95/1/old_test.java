@Test
  public void testGetClusters___OR_Predicate_ClusterNotFoundException() throws Exception {
    // member state mocks
    Injector injector = createStrictMock(Injector.class);
    Capture<AmbariManagementController> controllerCapture = new Capture<AmbariManagementController>();

    Cluster cluster = createNiceMock(Cluster.class);
    Cluster cluster2 = createNiceMock(Cluster.class);
    ClusterResponse response = createNiceMock(ClusterResponse.class);
    ClusterResponse response2 = createNiceMock(ClusterResponse.class);

    // requests
    ClusterRequest request1 = new ClusterRequest(null, "cluster1", "1", Collections.<String>emptySet());
    ClusterRequest request2 = new ClusterRequest(null, "cluster2", "1", Collections.<String>emptySet());
    ClusterRequest request3 = new ClusterRequest(null, "cluster3", "1", Collections.<String>emptySet());
    ClusterRequest request4 = new ClusterRequest(null, "cluster4", "1", Collections.<String>emptySet());

    Set<ClusterRequest> setRequests = new HashSet<ClusterRequest>();
    setRequests.add(request1);
    setRequests.add(request2);
    setRequests.add(request3);
    setRequests.add(request4);

    // expectations
    // constructor init
    injector.injectMembers(capture(controllerCapture));
    expect(injector.getInstance(Gson.class)).andReturn(null);
    expect(injector.getInstance(MaintenanceStateHelper.class)).andReturn(null);
    expect(injector.getInstance(KerberosHelper.class)).andReturn(createNiceMock(KerberosHelper.class));

    // getCluster
    expect(clusters.getCluster("cluster1")).andThrow(new ClusterNotFoundException("cluster1"));
    expect(clusters.getCluster("cluster2")).andReturn(cluster);
    expect(clusters.getCluster("cluster3")).andReturn(cluster2);
    expect(clusters.getCluster("cluster4")).andThrow(new ClusterNotFoundException("cluster4"));

    expect(cluster.convertToResponse()).andReturn(response);
    expect(cluster2.convertToResponse()).andReturn(response2);

    CredentialStoreService credentialStoreService = createNiceMock(CredentialStoreService.class);
    expect(credentialStoreService.isInitialized(anyObject(CredentialStoreType.class))).andReturn(true).anyTimes();

    // replay mocks
    replay(injector, clusters, cluster, cluster2, response, response2, credentialStoreService);

    //test
    AmbariManagementController controller = new AmbariManagementControllerImpl(null, clusters, injector);

    Field f = controller.getClass().getDeclaredField("credentialStoreService");
    f.setAccessible(true);
    f.set(controller, credentialStoreService);

    Set<ClusterResponse> setResponses = controller.getClusters(setRequests);

    // assert and verify
    assertSame(controller, controllerCapture.getValue());
    assertEquals(2, setResponses.size());
    assertTrue(setResponses.contains(response));
    assertTrue(setResponses.contains(response2));

    verify(injector, clusters, cluster, cluster2, response, response2, credentialStoreService);
  }