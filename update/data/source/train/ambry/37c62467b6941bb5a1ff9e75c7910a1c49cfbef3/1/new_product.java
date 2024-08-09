private static AsyncRequestResponseHandler getAsyncRequestResponseHandler(int requestWorkers) throws IOException {
    RequestResponseHandlerMetrics metrics = new RequestResponseHandlerMetrics(new MetricRegistry());
    AsyncRequestResponseHandler handler = new AsyncRequestResponseHandler(metrics);
    if (requestWorkers > 0) {
      if (restRequestService == null) {
        restRequestService = new MockRestRequestService(verifiableProperties, handler, router);
      }
      handler.setupRequestHandling(requestWorkers, restRequestService);
    }
    return handler;
  }