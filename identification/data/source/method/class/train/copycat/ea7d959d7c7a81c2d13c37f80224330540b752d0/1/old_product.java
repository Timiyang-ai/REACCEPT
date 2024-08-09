protected final <R extends Response> R logResponse(R response) {
    LOGGER.debug("{} - Sent {}", context.getCluster().getMember().serverAddress(), response);
    return response;
  }