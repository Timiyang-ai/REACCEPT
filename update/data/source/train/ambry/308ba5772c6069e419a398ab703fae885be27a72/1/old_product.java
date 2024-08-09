private void replenishConnections() {
    if (networkConfig.networkClientEnableConnectionReplenishment) {
      int connectionsInitiated = connectionTracker.replenishConnections(this::connect);
      networkMetrics.connectionReplenished.inc(connectionsInitiated);
    }
  }