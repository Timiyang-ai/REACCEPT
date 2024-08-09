public void getFeature(int lat, int lon) {
    try {
      info("*** GetFeature: lat={0} lon={1}", lat, lon);

      Point request = Point.newBuilder().setLatitude(lat).setLongitude(lon).build();
      Feature feature = blockingStub.getFeature(request);
      if (RouteGuideUtil.exists(feature)) {
        info("Found feature called \"{0}\" at {1}, {2}",
            feature.getName(),
            RouteGuideUtil.getLatitude(feature.getLocation()),
            RouteGuideUtil.getLongitude(feature.getLocation()));
      } else {
        info("Found no feature at {0}, {1}",
            RouteGuideUtil.getLatitude(feature.getLocation()),
            RouteGuideUtil.getLongitude(feature.getLocation()));
      }
    } catch (RuntimeException e) {
      logger.log(Level.WARNING, "RPC failed", e);
      throw e;
    }
  }