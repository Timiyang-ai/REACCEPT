void shutdownLbRpc(String message) {
    adsStream.cancelRpc(message, null);
    cancelRetryTimer();
  }