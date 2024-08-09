protected final <R extends Request> R logRequest(R request) {
    LOGGER.debug("{} - Received {}", context.getCluster().getMember().serverAddress(), request);
    return request;
  }