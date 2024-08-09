protected final <R extends Response> R logResponse(R response) {
    LOGGER.debug("{} - Sent {}", context.getAddress(), response);
    return response;
  }