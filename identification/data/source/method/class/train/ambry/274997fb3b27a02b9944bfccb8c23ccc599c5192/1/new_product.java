@Override
  public void close() {
    if (channelOpen.compareAndSet(true, false)) {
      resolveAllRemainingChunks(new ClosedChannelException());
    }
    if (channelEventListener != null) {
      channelEventListener.onEvent(EventType.Close);
    }
  }