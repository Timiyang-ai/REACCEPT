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
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }

    StringBuilder responseLog = new StringBuilder("Result: ");
    while (features.hasNext()) {
      Feature feature = features.next();
      responseLog.append(feature);
    }
    info(responseLog.toString());
  }