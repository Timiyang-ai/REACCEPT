  @Test
  public void listFeatures() throws Exception {
    // setup
    Rectangle rect = Rectangle.newBuilder()
        .setLo(Point.newBuilder().setLongitude(0).setLatitude(0).build())
        .setHi(Point.newBuilder().setLongitude(10).setLatitude(10).build())
        .build();
    Feature f1 = Feature.newBuilder()
        .setLocation(Point.newBuilder().setLongitude(-1).setLatitude(-1).build())
        .setName("f1")
        .build(); // not inside rect
    Feature f2 = Feature.newBuilder()
        .setLocation(Point.newBuilder().setLongitude(2).setLatitude(2).build())
        .setName("f2")
        .build();
    Feature f3 = Feature.newBuilder()
        .setLocation(Point.newBuilder().setLongitude(3).setLatitude(3).build())
        .setName("f3")
        .build();
    Feature f4 = Feature.newBuilder()
        .setLocation(Point.newBuilder().setLongitude(4).setLatitude(4).build())
        .build(); // unamed
    features.add(f1);
    features.add(f2);
    features.add(f3);
    features.add(f4);
    final List<Feature> result = new ArrayList<Feature>();
    final CountDownLatch latch = new CountDownLatch(1);
    StreamObserver<Feature> responseObserver =
        new StreamObserver<Feature>() {
          @Override
          public void onNext(Feature value) {
            result.add(value);
          }

          @Override
          public void onError(Throwable t) {
            fail();
          }

          @Override
          public void onCompleted() {
            latch.countDown();
          }
        };
    RouteGuideGrpc.RouteGuideStub stub = RouteGuideGrpc.newStub(inProcessChannel);

    // run
    stub.listFeatures(rect, responseObserver);
    assertTrue(latch.await(1, TimeUnit.SECONDS));

    // verify
    assertEquals(Arrays.asList(f2, f3), result);
  }