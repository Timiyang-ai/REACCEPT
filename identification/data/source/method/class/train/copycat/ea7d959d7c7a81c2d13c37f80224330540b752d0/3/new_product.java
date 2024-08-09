protected final <R extends Response> R logResponse(R response) {
    LOGGER.trace("{} - Sending {}", context.getCluster().member().address(), response);
    return response;
  }