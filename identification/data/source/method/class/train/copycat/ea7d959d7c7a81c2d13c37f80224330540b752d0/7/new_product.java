protected final <R extends Request> R logRequest(R request) {
    LOGGER.debug("{} - Received {}", controller.context().getCluster().getMember().serverAddress(), request);
    return request;
  }