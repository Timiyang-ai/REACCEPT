@Override
  public void close() {
    forceClose = true;
    onResponseComplete(new ClosedChannelException());
  }