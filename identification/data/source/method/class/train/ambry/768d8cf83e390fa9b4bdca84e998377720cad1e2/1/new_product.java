@Override
  public void close() {
    close(new ClosedChannelException());
  }