int replenishConnections(ConnectionFactory connectionFactory, int maxNewConnectionsPerHost) {
    int newConnections = 0;
    Iterator<HostPortPoolManager> iter = poolManagersBelowMinActiveConnections.iterator();
    while (iter.hasNext()) {
      HostPortPoolManager poolManager = iter.next();
      try {
        // avoid continuously attempting to connect to down nodes.
        if (poolManager.dataNodeId.getState() == HardwareState.AVAILABLE) {
          int newConnectionsToHost = 0;
          while (newConnectionsToHost < maxNewConnectionsPerHost && !poolManager.hasMinActiveConnections()) {
            String connId = connectionFactory.connect(poolManager.host, poolManager.port);
            poolManager.incrementPoolCount();
            connectionIdToPoolManager.put(connId, poolManager);
            totalManagedConnectionsCount++;
            newConnections++;
            newConnectionsToHost++;
          }
          if (poolManager.hasMinActiveConnections()) {
            iter.remove();
          }
        }
      } catch (IOException e) {
        LOGGER.warn("Encountered exception while replenishing connections to {}:{}.", poolManager.host,
            poolManager.port.getPort(), e);
      }
    }
    return newConnections;
  }