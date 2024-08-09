public ReadableStreamChannel getReplicas(String blobId, RestResponseChannel restResponseChannel)
      throws RestServiceException {
    logger.trace("Getting replicas of blob ID - {}", blobId);
    long startTime = System.currentTimeMillis();
    ReadableStreamChannel channel = null;
    try {
      String replicaStr = getReplicas(blobId).toString();
      restResponseChannel.setHeader(RestUtils.Headers.CONTENT_TYPE, "application/json");
      restResponseChannel.setHeader(RestUtils.Headers.CONTENT_LENGTH, replicaStr.length());
      channel = new ByteBufferReadableStreamChannel(ByteBuffer.wrap(replicaStr.getBytes()));
    } finally {
      adminMetrics.getReplicasProcessingTimeInMs.update(System.currentTimeMillis() - startTime);
    }
    return channel;
  }