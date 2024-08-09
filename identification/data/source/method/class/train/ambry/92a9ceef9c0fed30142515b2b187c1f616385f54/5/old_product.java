@Override
  public int write(ByteBuffer src)
      throws ClosedChannelException {
    if (!src.hasArray()) {
      throw new IllegalArgumentException(
          "NettyResponseChannel does not work with ByteBuffers that are not backed by byte arrays");
    }

    if (!responseMetadataWritten.get()) {
      maybeWriteResponseMetadata();
    }
    verifyChannelActive();
    int bytesWritten = 0;
    if (ctx.channel().isWritable()) {
      emptyingFlushRequired.set(true);
      int bytesToWrite = Math.min(src.remaining(), ctx.channel().config().getWriteBufferLowWaterMark());
      ByteBuf buf =
          Unpooled.wrappedBuffer(src.array(), src.arrayOffset() + src.position(), bytesToWrite).order(src.order());
      logger.trace("Writing {} bytes to channel {}", bytesToWrite, ctx.channel());
      ChannelFuture writeFuture = writeToChannel(new DefaultHttpContent(buf), ChannelWriteType.Safe);
      if (!writeFuture.isDone() || writeFuture.isSuccess()) {
        bytesWritten = bytesToWrite;
        src.position(src.position() + bytesToWrite);
      }
    } else if (emptyingFlushRequired.compareAndSet(true, false)) {
      // TODO: metrics.
      flush();
    }
    return bytesWritten;
  }