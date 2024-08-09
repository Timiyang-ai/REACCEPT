protected final <R extends Request> R logRequest(R request) {
    LOGGER.debug("{} - Received {}", context.getAddress(), request);
    return request;
  }