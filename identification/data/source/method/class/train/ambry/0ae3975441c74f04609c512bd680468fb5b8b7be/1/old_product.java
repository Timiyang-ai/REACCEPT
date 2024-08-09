private static AsyncRequestResponseHandler getAsyncRequestResponseHandler(int requestWorkers)
      throws IOException {
    RestServerMetrics serverMetrics =
        new RestServerMetrics(new MetricRegistry(), new RestServerState("/healthCheckUri"));
    AsyncRequestResponseHandler handler = new AsyncRequestResponseHandler(serverMetrics);
    if (requestWorkers > 0) {
      if (blobStorageService == null) {
        blobStorageService = new MockBlobStorageService(verifiableProperties, handler, router);
      }
      handler.setupRequestHandling(requestWorkers, blobStorageService);
    }
    return handler;
  }