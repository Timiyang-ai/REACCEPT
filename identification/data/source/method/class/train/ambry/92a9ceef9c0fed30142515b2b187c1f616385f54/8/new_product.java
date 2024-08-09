@Override
  public int write(ByteBuffer src)
      throws ClosedChannelException {
    long writeProcessingStartTime = System.currentTimeMillis();
    // needed to avoid double counting.
    long responseMetadataWriteTime = 0;
    long channelWriteTime = 0;
    try {
      if (!src.hasArray()) {
        throw new IllegalArgumentException(
            "NettyResponseChannel does not work with ByteBuffers that are not backed by byte arrays");
      }

      if (!responseMetadataWritten.get()) {
        long responseMetadataWriteStartTime = System.currentTimeMillis();
        maybeWriteResponseMetadata();
        responseMetadataWriteTime = System.currentTimeMillis() - responseMetadataWriteStartTime;
      }
      verifyChannelActive();
      int bytesWritten = 0;
      if (ctx.channel().isWritable()) {
        emptyingFlushRequired.set(true);
        int bytesToWrite = Math.min(src.remaining(), ctx.channel().config().getWriteBufferLowWaterMark());
        ByteBuf buf =
            Unpooled.wrappedBuffer(src.array(), src.arrayOffset() + src.position(), bytesToWrite).order(src.order());
        logger.trace("Writing {} bytes to channel {}", bytesToWrite, ctx.channel());
        long channelWriteStartTime = System.currentTimeMillis();
        ChannelFuture writeFuture = writeToChannel(new DefaultHttpContent(buf), ChannelWriteType.Safe);
        channelWriteTime = System.currentTimeMillis() - channelWriteStartTime;
        if (!writeFuture.isDone() || writeFuture.isSuccess()) {
          bytesWritten = bytesToWrite;
          src.position(src.position() + bytesToWrite);
        }
      } else if (emptyingFlushRequired.compareAndSet(true, false)) {
        nettyMetrics.emptyingFlushCount.inc();
        flush();
      }
      nettyMetrics.bytesWriteRate.mark(bytesWritten);
      return bytesWritten;
    } finally {
      long writeProcessingTime =
          System.currentTimeMillis() - writeProcessingStartTime - responseMetadataWriteTime - channelWriteTime;
      nettyMetrics.writeProcessingTimeInMs.update(writeProcessingTime);
      if (request != null) {
        request.getMetricsTracker().nioMetricsTracker.addToResponseProcessingTime(writeProcessingTime);
      }
    }
  }