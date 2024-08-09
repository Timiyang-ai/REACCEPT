  @Test
  public void listFeatures() {
    final Feature responseFeature1 = Feature.newBuilder().setName("feature 1").build();
    final Feature responseFeature2 = Feature.newBuilder().setName("feature 2").build();
    final AtomicReference<Rectangle> rectangleDelivered = new AtomicReference<Rectangle>();

    // implement the fake service
    RouteGuideImplBase listFeaturesImpl =
        new RouteGuideImplBase() {
          @Override
          public void listFeatures(Rectangle rectangle, StreamObserver<Feature> responseObserver) {
            rectangleDelivered.set(rectangle);

            // send two response messages
            responseObserver.onNext(responseFeature1);
            responseObserver.onNext(responseFeature2);

            // complete the response
            responseObserver.onCompleted();
          }
        };
    serviceRegistry.addService(listFeaturesImpl);

    client.listFeatures(1, 2, 3, 4);

    assertEquals(Rectangle.newBuilder()
                     .setLo(Point.newBuilder().setLatitude(1).setLongitude(2).build())
                     .setHi(Point.newBuilder().setLatitude(3).setLongitude(4).build())
                     .build(),
                 rectangleDelivered.get());
    verify(testHelper).onMessage(responseFeature1);
    verify(testHelper).onMessage(responseFeature2);
    verify(testHelper, never()).onRpcError(any(Throwable.class));
  }