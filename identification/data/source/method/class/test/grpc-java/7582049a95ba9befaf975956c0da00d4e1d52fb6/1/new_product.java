void resetConnectBackoff() {
    syncContext.execute(new Runnable() {
      @Override
      public void run() {
        if (state.getState() != TRANSIENT_FAILURE) {
          return;
        }
        cancelReconnectTask();
        channelLogger.log(ChannelLogLevel.INFO, "CONNECTING; backoff interrupted");
        gotoNonErrorState(CONNECTING);
        startNewTransport();
      }
    });
  }