  @Test
  public void getFeature() {
    Point requestPoint =  Point.newBuilder().setLatitude(-1).setLongitude(-1).build();
    Point responsePoint = Point.newBuilder().setLatitude(-123).setLongitude(-123).build();
    final AtomicReference<Point> pointDelivered = new AtomicReference<Point>();
    final Feature responseFeature =
        Feature.newBuilder().setName("dummyFeature").setLocation(responsePoint).build();

    // implement the fake service
    RouteGuideImplBase getFeatureImpl =
        new RouteGuideImplBase() {
          @Override
          public void getFeature(Point point, StreamObserver<Feature> responseObserver) {
            pointDelivered.set(point);
            responseObserver.onNext(responseFeature);
            responseObserver.onCompleted();
          }
        };
    serviceRegistry.addService(getFeatureImpl);

    client.getFeature(-1, -1);

    assertEquals(requestPoint, pointDelivered.get());
    verify(testHelper).onMessage(responseFeature);
    verify(testHelper, never()).onRpcError(any(Throwable.class));
  }