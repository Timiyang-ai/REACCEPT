public void shutdown() {
    if (shutdown) {
      return;
    }
    cache.clear();
    cleanupThread.shutdown();
    stats.shutdown();
    dispatcher.shutdown();
    for (DeferredRequest deferredRequest : targetToDeferredRequest.values()) {
      deferredRequest.cancel();
    }
    targetToDeferredRequest.clear();
    if (this == singleton) {
      singleton = null;
    }
    shutdown = true;
  }