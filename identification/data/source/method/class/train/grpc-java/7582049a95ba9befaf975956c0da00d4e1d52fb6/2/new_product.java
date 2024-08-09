void resetConnectBackoff() {
    try {
      synchronized (lock) {
        if (state.getState() != TRANSIENT_FAILURE) {
          return;
        }
        cancelReconnectTask();
        channelLogger.log(ChannelLogLevel.INFO, "CONNECTING; backoff interrupted");
        gotoNonErrorState(CONNECTING);
        startNewTransport();
      }
    } finally {
      syncContext.drain();
    }
  }