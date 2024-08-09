public void getFeature(int lat, int lon) {
    info("*** GetFeature: lat={0} lon={1}", lat, lon);

    Point request = Point.newBuilder().setLatitude(lat).setLongitude(lon).build();

    Feature feature;
    try {
      feature = blockingStub.getFeature(request);
    } catch (StatusRuntimeException e) {
      logger.log(Level.WARNING, "RPC failed: {0}", e.getStatus());
      return;
    }
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
  }