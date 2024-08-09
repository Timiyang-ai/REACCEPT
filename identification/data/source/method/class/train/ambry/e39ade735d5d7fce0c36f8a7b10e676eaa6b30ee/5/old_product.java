public void handleGet(MessageInfo messageInfo)
      throws RestServiceException {
    RestRequest request = messageInfo.getRestRequest();
    logger.trace("Handling get request - " + request.getUri());
    if (!isCustomOperation(request)) {
      // TODO: this is a traditional get
      throw new IllegalStateException("Traditional GET not implemented");
    } else {
      handleCustomGetOperation(messageInfo);
    }
  }