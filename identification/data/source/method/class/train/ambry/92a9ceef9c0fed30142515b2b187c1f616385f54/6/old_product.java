@Override
  public int write(ByteBuffer src)
      throws ClosedChannelException {
    if (!src.hasArray()) {
      throw new IllegalArgumentException(
          "NettyResponseChannel does not work with ByteBuffers that are not backed by byte arrays");
    }

    if (!responseMetadataWritten.get()) {
      maybeWriteResponseMetadata(responseMetadata);
    }
    verifyChannelActive();
    int bytesWritten = 0;
    if (ctx.channel().isWritable()) {
      emptyingFlushRequired.set(true);
      int bytesToWrite = Math.min(src.remaining(), ctx.channel().config().getWriteBufferLowWaterMark());
      logger.trace("Adding {} bytes of data to response on channel {}", bytesToWrite, ctx.channel());
      ByteBuf buf =
          Unpooled.wrappedBuffer(src.array(), src.arrayOffset() + src.position(), bytesToWrite).order(src.order());
      ChannelFuture writeFuture = writeToChannel(new DefaultHttpContent(buf), ChannelWriteType.Safe);
      if (!writeFuture.isDone() || writeFuture.isSuccess()) {
        bytesWritten = bytesToWrite;
        src.position(src.position() + bytesToWrite);
      }
    } else if (emptyingFlushRequired.compareAndSet(true, false)) {
      flush();
    }
    return bytesWritten;
  }