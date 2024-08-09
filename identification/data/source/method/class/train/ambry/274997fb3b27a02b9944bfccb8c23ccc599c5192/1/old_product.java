@Override
  public void close() {
    if (channelOpen.compareAndSet(true, false)) {
      resolveAllRemainingChunks(new ClosedChannelException());
    }
  }