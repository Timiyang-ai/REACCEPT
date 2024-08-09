public void listFeatures(int lowLat, int lowLon, int hiLat, int hiLon) {
    info("*** ListFeatures: lowLat={0} lowLon={1} hiLat={2} hiLon={3}", lowLat, lowLon, hiLat,
        hiLon);

    Rectangle request =
        Rectangle.newBuilder()
            .setLo(Point.newBuilder().setLatitude(lowLat).setLongitude(lowLon).build())
            .setHi(Point.newBuilder().setLatitude(hiLat).setLongitude(hiLon).build()).build();
    Iterator<Feature> features;
    try {
      features = blockingStub.listFeatures(request);
      for (int i = 1; features.hasNext(); i++) {
        Feature feature = features.next();
        info("Result #" + i + ": {0}", feature);
        if (testHelper != null) {
          testHelper.onMessage(feature);
        }
      }
    } catch (StatusRuntimeException e) {
      warning("RPC failed: {0}", e.getStatus());
      if (testHelper != null) {
        testHelper.onRpcError(e);
      }
    }
  }