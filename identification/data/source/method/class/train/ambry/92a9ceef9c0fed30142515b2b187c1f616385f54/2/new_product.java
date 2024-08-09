@Override
  public Future<Long> write(ByteBuffer src, Callback<Long> callback) {
    long writeProcessingStartTime = System.currentTimeMillis();
    if (!responseMetadataWritten.get()) {
      maybeWriteResponseMetadata(responseMetadata, responseMetadataWriteListener);
    }
    Chunk chunk = new Chunk(src, callback);
    chunksToWrite.add(chunk);
    if (!isOpen()) {
      // the isOpen() check is not before addition to the queue because chunks need to be acknowledged in the order
      // they were received. If we don't add it to the queue and clean up, chunks may be acknowledged out of order.
      logger.debug("Scheduling a chunk cleanup on channel {}", ctx.channel());
      writeFuture.addListener(cleanupCallback);
    } else {
      chunkedWriteHandler.resumeTransfer();
    }

    long writeProcessingTime = System.currentTimeMillis() - writeProcessingStartTime;
    nettyMetrics.writeProcessingTimeInMs.update(writeProcessingTime);
    if (request != null) {
      request.getMetricsTracker().nioMetricsTracker.addToResponseProcessingTime(writeProcessingTime);
    }
    return chunk.future;
  }