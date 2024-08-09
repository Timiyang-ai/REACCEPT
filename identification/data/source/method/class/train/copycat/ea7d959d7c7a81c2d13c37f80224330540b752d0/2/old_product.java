protected final <R extends Request> R logRequest(R request) {
    LOGGER.debug("{} - Received {}", context.getCluster().member().address(), request);
    return request;
  }