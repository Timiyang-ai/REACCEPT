  @Test
  public void recordRoute() throws Exception {
    client.setRandom(noRandomness);
    Point point1 = Point.newBuilder().setLatitude(1).setLongitude(1).build();
    Point point2 = Point.newBuilder().setLatitude(2).setLongitude(2).build();
    Point point3 = Point.newBuilder().setLatitude(3).setLongitude(3).build();
    Feature requestFeature1 =
        Feature.newBuilder().setLocation(point1).build();
    Feature requestFeature2 =
        Feature.newBuilder().setLocation(point2).build();
    Feature requestFeature3 =
        Feature.newBuilder().setLocation(point3).build();
    final List<Feature> features = Arrays.asList(
        requestFeature1, requestFeature2, requestFeature3);
    final List<Point> pointsDelivered = new ArrayList<>();
    final RouteSummary fakeResponse = RouteSummary
        .newBuilder()
        .setPointCount(7)
        .setFeatureCount(8)
        .setDistance(9)
        .setElapsedTime(10)
        .build();

    // implement the fake service
    RouteGuideImplBase recordRouteImpl =
        new RouteGuideImplBase() {
          @Override
          public StreamObserver<Point> recordRoute(
              final StreamObserver<RouteSummary> responseObserver) {
            StreamObserver<Point> requestObserver = new StreamObserver<Point>() {
              @Override
              public void onNext(Point value) {
                pointsDelivered.add(value);
              }

              @Override
              public void onError(Throwable t) {
              }

              @Override
              public void onCompleted() {
                responseObserver.onNext(fakeResponse);
                responseObserver.onCompleted();
              }
            };

            return requestObserver;
          }
        };
    serviceRegistry.addService(recordRouteImpl);

    // send requestFeature1, requestFeature2, requestFeature3, and then requestFeature1 again
    client.recordRoute(features, 4);

    assertEquals(
        Arrays.asList(
            requestFeature1.getLocation(),
            requestFeature2.getLocation(),
            requestFeature3.getLocation(),
            requestFeature1.getLocation()),
        pointsDelivered);
    verify(testHelper).onMessage(fakeResponse);
    verify(testHelper, never()).onRpcError(any(Throwable.class));
  }