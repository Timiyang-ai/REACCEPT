public void recordRoute(List<Feature> features, int numPoints) throws Exception {
    info("*** RecordRoute");
    final SettableFuture<Void> finishFuture = SettableFuture.create();
    StreamObserver<RouteSummary> responseObserver = new StreamObserver<RouteSummary>() {
      @Override
      public void onValue(RouteSummary summary) {
        info("Finished trip with {0} points. Passed {1} features. "
            + "Travelled {2} meters. It took {3} seconds.", summary.getPointCount(),
            summary.getFeatureCount(), summary.getDistance(), summary.getElapsedTime());
      }

      @Override
      public void onError(Throwable t) {
        finishFuture.setException(t);
      }

      @Override
      public void onCompleted() {
        finishFuture.set(null);
      }
    };

    StreamObserver<Point> requestObserver = asyncStub.recordRoute(responseObserver);
    try {
      // Send numPoints points randomly selected from the features list.
      StringBuilder numMsg = new StringBuilder();
      Random rand = new Random();
      for (int i = 0; i < numPoints; ++i) {
        int index = rand.nextInt(features.size());
        Point point = features.get(index).getLocation();
        info("Visiting point {0}, {1}", RouteGuideUtil.getLatitude(point),
            RouteGuideUtil.getLongitude(point));
        requestObserver.onValue(point);
        // Sleep for a bit before sending the next one.
        Thread.sleep(rand.nextInt(1000) + 500);
        if (finishFuture.isDone()) {
          break;
        }
      }
      info(numMsg.toString());
      requestObserver.onCompleted();

      finishFuture.get();
      info("Finished RecordRoute");
    } catch (Exception e) {
      requestObserver.onError(e);
      logger.log(Level.WARNING, "RecordRoute Failed", e);
      throw e;
    }
  }