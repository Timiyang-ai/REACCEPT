private void close()
      throws RestServiceException {
    if (!channelClosed.get() && ctx.channel().isOpen()) {
      try {
        channelWriteLock.lockInterruptibly();
        channelClosed.set(true);
        // Waits for the last write operation performed by this class to succeed before closing.
        // This is NOT blocking.
        lastWriteFuture.addListener(ChannelFutureListener.CLOSE);
        logger.trace("Requested closing of channel {}", ctx.channel());
      } catch (InterruptedException e) {
        nettyMetrics.channelCloseLockInterruptedError.inc();
        throw new RestServiceException("Internal channel close lock acquiring interrupted. Did not close channel", e,
            RestServiceErrorCode.OperationInterrupted);
      } finally {
        if (channelWriteLock.isHeldByCurrentThread()) {
          channelWriteLock.unlock();
        }
      }
    }
  }