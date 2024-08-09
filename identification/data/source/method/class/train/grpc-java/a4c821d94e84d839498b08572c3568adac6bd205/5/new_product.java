public void recordRoute(List<Feature> features, int numPoints) throws InterruptedException {
    info("*** RecordRoute");
    final CountDownLatch finishLatch = new CountDownLatch(1);
    StreamObserver<RouteSummary> responseObserver = new StreamObserver<RouteSummary>() {
      @Override
      public void onNext(RouteSummary summary) {
        info("Finished trip with {0} points. Passed {1} features. "
            + "Travelled {2} meters. It took {3} seconds.", summary.getPointCount(),
            summary.getFeatureCount(), summary.getDistance(), summary.getElapsedTime());
        if (testHelper != null) {
          testHelper.onMessage(summary);
        }
      }

      @Override
      public void onError(Throwable t) {
        warning("RecordRoute Failed: {0}", Status.fromThrowable(t));
        if (testHelper != null) {
          testHelper.onRpcError(t);
        }
        finishLatch.countDown();
      }

      @Override
      public void onCompleted() {
        info("Finished RecordRoute");
        finishLatch.countDown();
      }
    };

    StreamObserver<Point> requestObserver = asyncStub.recordRoute(responseObserver);
    try {
      // Send numPoints points randomly selected from the features list.
      for (int i = 0; i < numPoints; ++i) {
        int index = random.nextInt(features.size());
        Point point = features.get(index).getLocation();
        info("Visiting point {0}, {1}", RouteGuideUtil.getLatitude(point),
            RouteGuideUtil.getLongitude(point));
        requestObserver.onNext(point);
        // Sleep for a bit before sending the next one.
        Thread.sleep(random.nextInt(1000) + 500);
        if (finishLatch.getCount() == 0) {
          // RPC completed or errored before we finished sending.
          // Sending further requests won't error, but they will just be thrown away.
          return;
        }
      }
    } catch (RuntimeException e) {
      // Cancel RPC
      requestObserver.onError(e);
      throw e;
    }
    // Mark the end of requests
    requestObserver.onCompleted();

    // Receiving happens asynchronously
    if (!finishLatch.await(1, TimeUnit.MINUTES)) {
      warning("recordRoute can not finish within 1 minutes");
    }
  }