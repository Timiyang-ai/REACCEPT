public void listFeatures(int lowLat, int lowLon, int hiLat, int hiLon) {
    try {
      info("*** ListFeatures: lowLat={0} lowLon={1} hiLat={2} hiLon={3}", lowLat, lowLon, hiLat,
          hiLon);

      Rectangle request =
          Rectangle.newBuilder()
              .setLo(Point.newBuilder().setLatitude(lowLat).setLongitude(lowLon).build())
              .setHi(Point.newBuilder().setLatitude(hiLat).setLongitude(hiLon).build()).build();
      Iterator<Feature> features = blockingStub.listFeatures(request);

      StringBuilder responseLog = new StringBuilder("Result: ");
      while (features.hasNext()) {
        Feature feature = features.next();
        responseLog.append(feature);
      }
      info(responseLog.toString());
    } catch (RuntimeException e) {
      logger.log(Level.WARNING, "RPC failed", e);
      throw e;
    }
  }