protected final <R extends Request> R logRequest(R request) {
    LOGGER.debug("{} - Received {}", context.getMember().serverAddress(), request);
    return request;
  }