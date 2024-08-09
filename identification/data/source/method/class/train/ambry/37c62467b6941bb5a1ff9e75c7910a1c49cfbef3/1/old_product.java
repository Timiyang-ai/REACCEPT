private static AsyncRequestResponseHandler getAsyncRequestResponseHandler(int requestWorkers) throws IOException {
    RequestResponseHandlerMetrics metrics = new RequestResponseHandlerMetrics(new MetricRegistry());
    AsyncRequestResponseHandler handler = new AsyncRequestResponseHandler(metrics);
    if (requestWorkers > 0) {
      if (blobStorageService == null) {
        blobStorageService = new MockBlobStorageService(verifiableProperties, handler, router);
      }
      handler.setupRequestHandling(requestWorkers, blobStorageService);
    }
    return handler;
  }