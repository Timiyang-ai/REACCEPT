private void replenishConnections() {
    long currentTimeMs = time.milliseconds();
    if (networkConfig.networkClientEnableConnectionReplenishment && currentTimeMs >= nextReplenishMs) {
      int connectionsInitiated = connectionTracker.replenishConnections(this::connect,
          networkConfig.networkClientMaxReplenishmentPerHostPerSecond);
      if (connectionsInitiated > 0) {
        networkMetrics.connectionReplenished.inc(connectionsInitiated);
        logger.debug("replenishConnections initiated {} connections", connectionsInitiated);
        nextReplenishMs = currentTimeMs + Time.MsPerSec;
      }
    }
  }