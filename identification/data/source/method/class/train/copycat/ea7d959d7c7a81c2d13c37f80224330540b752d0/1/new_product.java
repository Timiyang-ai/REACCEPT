protected final <R extends Response> R logResponse(R response) {
    LOGGER.debug("{} - Sent {}", controller.context().getCluster().getMember().serverAddress(), response);
    return response;
  }