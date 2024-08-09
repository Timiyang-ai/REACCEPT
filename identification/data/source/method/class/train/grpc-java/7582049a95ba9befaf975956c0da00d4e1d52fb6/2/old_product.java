void resetConnectBackoff() {
    try {
      synchronized (lock) {
        if (state.getState() != TRANSIENT_FAILURE) {
          return;
        }
        cancelReconnectTask();
        gotoNonErrorState(CONNECTING);
        startNewTransport();
      }
    } finally {
      syncContext.drain();
    }
  }