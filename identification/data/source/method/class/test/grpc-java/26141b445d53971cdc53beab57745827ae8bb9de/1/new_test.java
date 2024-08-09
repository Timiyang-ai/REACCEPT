  @Test
  public void getFeature() {
    Point point = Point.newBuilder().setLongitude(1).setLatitude(1).build();
    Feature unnamedFeature = Feature.newBuilder()
        .setName("").setLocation(point).build();
    RouteGuideGrpc.RouteGuideBlockingStub stub = RouteGuideGrpc.newBlockingStub(inProcessChannel);

    // feature not found in the server
    Feature feature = stub.getFeature(point);

    assertEquals(unnamedFeature, feature);

    // feature found in the server
    Feature namedFeature = Feature.newBuilder()
        .setName("name").setLocation(point).build();
    features.add(namedFeature);

    feature = stub.getFeature(point);

    assertEquals(namedFeature, feature);
  }