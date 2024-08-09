@Override
  public void close() {
    if (isOpen()) {
      try {
        channelWriteLock.lock();
        channelOpen.set(false);
        // Waits for the last write operation performed by this class to succeed before closing.
        // This is NOT blocking.
        lastWriteFuture.addListener(ChannelFutureListener.CLOSE);
        logger.trace("Requested closing of channel {}", ctx.channel());
      } finally {
        channelWriteLock.unlock();
      }
    }
  }