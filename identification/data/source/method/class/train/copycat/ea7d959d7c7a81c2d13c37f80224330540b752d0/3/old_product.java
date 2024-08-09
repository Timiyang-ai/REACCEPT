protected final <R extends Response> R logResponse(R response) {
    LOGGER.debug("{} - Sent {}", context.getCluster().member().address(), response);
    return response;
  }