ReadableStreamChannel getReplicas(String blobId, RestResponseChannel restResponseChannel)
      throws RestServiceException {
    logger.trace("Getting replicas of blob ID - {}", blobId);
    long startTime = System.currentTimeMillis();
    ReadableStreamChannel channel = null;
    try {
      byte[] replicasResponseBytes = getReplicas(blobId).toString().getBytes();
      restResponseChannel.setHeader(RestUtils.Headers.CONTENT_TYPE, "application/json");
      restResponseChannel.setHeader(RestUtils.Headers.CONTENT_LENGTH, replicasResponseBytes.length);
      channel = new ByteBufferReadableStreamChannel(ByteBuffer.wrap(replicasResponseBytes));
    } finally {
      metrics.getReplicasProcessingTimeInMs.update(System.currentTimeMillis() - startTime);
    }
    return channel;
  }