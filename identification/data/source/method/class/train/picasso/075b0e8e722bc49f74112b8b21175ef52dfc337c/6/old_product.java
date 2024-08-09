public void shutdown() {
    if (shutdown) {
      return;
    }
    cache.clear();
    cleanupThread.shutdown();
    stats.shutdown();
    dispatcher.shutdown();
    if (this == singleton) {
      singleton = null;
    }
    shutdown = true;
  }