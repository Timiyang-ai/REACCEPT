protected final <R extends Request> R logRequest(R request) {
    LOGGER.trace("{} - Received {}", context.getCluster().member().address(), request);
    return request;
  }