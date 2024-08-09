private static AsyncRequestResponseHandler getAsyncRequestResponseHandler(int requestWorkers, int responseWorkers)
      throws IOException {
    RestServerMetrics serverMetrics = new RestServerMetrics(new MetricRegistry());
    AsyncRequestResponseHandler handler = new AsyncRequestResponseHandler(serverMetrics);
    if(blobStorageService == null) {
      blobStorageService = new MockBlobStorageService(verifiableProperties, handler, router);
    }
    handler.setRequestWorkersCount(requestWorkers);
    handler.setResponseWorkersCount(responseWorkers);
    handler.setBlobStorageService(blobStorageService);
    return handler;
  }