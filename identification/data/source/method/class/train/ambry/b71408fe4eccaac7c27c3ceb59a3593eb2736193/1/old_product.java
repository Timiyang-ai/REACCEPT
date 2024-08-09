@Override
  public void close() {
    onResponseComplete(new ClosedChannelException());
  }