public void shutdown() {
    if (this == singleton) {
      throw new UnsupportedOperationException("Default singleton instance cannot be shutdown.");
    }
    if (shutdown) {
      return;
    }
    cache.clear();
    cleanupThread.shutdown();
    stats.shutdown();
    dispatcher.shutdown();
    for (DeferredRequestCreator deferredRequestCreator : targetToDeferredRequest.values()) {
      deferredRequestCreator.cancel();
    }
    targetToDeferredRequest.clear();
    shutdown = true;
  }