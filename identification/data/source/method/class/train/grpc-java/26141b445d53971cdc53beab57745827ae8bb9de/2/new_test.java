  @Test
  public void recordRoute() {
    Point p1 = Point.newBuilder().setLongitude(1000).setLatitude(1000).build();
    Point p2 = Point.newBuilder().setLongitude(2000).setLatitude(2000).build();
    Point p3 = Point.newBuilder().setLongitude(3000).setLatitude(3000).build();
    Point p4 = Point.newBuilder().setLongitude(4000).setLatitude(4000).build();
    Feature f1 = Feature.newBuilder().setLocation(p1).build(); // unamed
    Feature f2 = Feature.newBuilder().setLocation(p2).setName("f2").build();
    Feature f3 = Feature.newBuilder().setLocation(p3).setName("f3").build();
    Feature f4 = Feature.newBuilder().setLocation(p4).build(); // unamed
    features.add(f1);
    features.add(f2);
    features.add(f3);
    features.add(f4);

    @SuppressWarnings("unchecked")
    StreamObserver<RouteSummary> responseObserver =
        (StreamObserver<RouteSummary>) mock(StreamObserver.class);
    RouteGuideGrpc.RouteGuideStub stub = RouteGuideGrpc.newStub(inProcessChannel);
    ArgumentCaptor<RouteSummary> routeSummaryCaptor = ArgumentCaptor.forClass(RouteSummary.class);

    StreamObserver<Point> requestObserver = stub.recordRoute(responseObserver);

    requestObserver.onNext(p1);
    requestObserver.onNext(p2);
    requestObserver.onNext(p3);
    requestObserver.onNext(p4);

    verify(responseObserver, never()).onNext(any(RouteSummary.class));

    requestObserver.onCompleted();

    // allow some ms to let client receive the response. Similar usage later on.
    verify(responseObserver, timeout(100)).onNext(routeSummaryCaptor.capture());
    RouteSummary summary = routeSummaryCaptor.getValue();
    assertEquals(45, summary.getDistance()); // 45 is the hard coded distance from p1 to p4.
    assertEquals(2, summary.getFeatureCount());
    verify(responseObserver, timeout(100)).onCompleted();
    verify(responseObserver, never()).onError(any(Throwable.class));
  }