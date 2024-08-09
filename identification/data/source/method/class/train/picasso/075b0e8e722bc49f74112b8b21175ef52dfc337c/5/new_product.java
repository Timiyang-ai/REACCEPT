public void shutdown() {
    if (shutdown) {
      return;
    }
    cache.clear();
    stats.shutdown();
    dispatcher.shutdown();
    if (closeableCache != null) {
      try {
        closeableCache.close();
      } catch (IOException ignored) {
      }
    }
    for (DeferredRequestCreator deferredRequestCreator : targetToDeferredRequestCreator.values()) {
      deferredRequestCreator.cancel();
    }
    targetToDeferredRequestCreator.clear();
    shutdown = true;
  }