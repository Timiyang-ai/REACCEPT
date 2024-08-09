private static AsyncRequestResponseHandler getAsyncRequestResponseHandler(BlobStorageService blobStorageService,
      int requestWorkers, int responseWorkers)
      throws IOException {
    RestServerMetrics serverMetrics = new RestServerMetrics(new MetricRegistry());
    AsyncRequestResponseHandler handler = new AsyncRequestResponseHandler(serverMetrics);
    handler.setRequestWorkersCount(requestWorkers);
    handler.setResponseWorkersCount(responseWorkers);
    if (blobStorageService != null) {
      handler.setBlobStorageService(blobStorageService);
    }
    return handler;
  }