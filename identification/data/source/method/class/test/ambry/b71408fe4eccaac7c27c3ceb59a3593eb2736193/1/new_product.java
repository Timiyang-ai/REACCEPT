@Override
  public void close() {
    maybeCloseRequest(true);
    closeResponseChannel();
    maybeCloseNetworkChannel(true);
  }