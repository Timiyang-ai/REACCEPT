public void shutdown() {
    if (shutdown) {
      return;
    }
    cache.clear();
    cleanupThread.shutdown();
    stats.shutdown();
    dispatcher.shutdown();
    for (DeferredRequestCreator deferredRequestCreator : targetToDeferredRequestCreator.values()) {
      deferredRequestCreator.cancel();
    }
    targetToDeferredRequestCreator.clear();
    shutdown = true;
  }