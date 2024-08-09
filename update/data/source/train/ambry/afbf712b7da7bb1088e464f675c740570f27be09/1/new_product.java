ReadableStreamChannel getReplicas(String blobId, RestResponseChannel restResponseChannel)
      throws RestServiceException {
    logger.trace("Getting replicas of blob ID - {}", blobId);
    long startTime = System.currentTimeMillis();
    ReadableStreamChannel channel = null;
    try {
      channel = FrontendUtils.serializeJsonToChannel(getReplicas(blobId));
      restResponseChannel.setHeader(RestUtils.Headers.CONTENT_TYPE, RestUtils.JSON_CONTENT_TYPE);
      restResponseChannel.setHeader(RestUtils.Headers.CONTENT_LENGTH, channel.getSize());
    } finally {
      metrics.getReplicasProcessingTimeInMs.update(System.currentTimeMillis() - startTime);
    }
    return channel;
  }