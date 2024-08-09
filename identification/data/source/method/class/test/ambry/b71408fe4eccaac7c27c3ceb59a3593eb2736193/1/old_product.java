@Override
  public void close() {
    closeResponseChannel();
    maybeCloseNetworkChannel(true);
    if (request != null) {
      try {
        request.close();
      } catch (IOException e) {
        nettyMetrics.resourceReleaseError.inc();
        logger.error("Error closing request", e);
      }
    }
  }