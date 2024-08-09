protected final <R extends Response> R logResponse(R response) {
    LOGGER.debug("{} - Sent {}", context.getMember().serverAddress(), response);
    return response;
  }