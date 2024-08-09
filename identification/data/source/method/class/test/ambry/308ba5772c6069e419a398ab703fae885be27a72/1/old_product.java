int replenishConnections(ConnectionFactory connectionFactory) {
    int connectionsInitiated = 0;
    Iterator<HostPortPoolManager> iter = poolManagersBelowMinActiveConnections.iterator();
    while (iter.hasNext()) {
      HostPortPoolManager poolManager = iter.next();
      try {
        // avoid continuously attempting to connect to down nodes.
        if (poolManager.dataNodeId.getState() == HardwareState.AVAILABLE) {
          while (!poolManager.hasMinActiveConnections()) {
            String connId = connectionFactory.connect(poolManager.host, poolManager.port);
            poolManager.incrementPoolCount();
            connectionIdToPoolManager.put(connId, poolManager);
            totalManagedConnectionsCount++;
            connectionsInitiated++;
          }
          iter.remove();
        }
      } catch (IOException e) {
        LOGGER.warn("Encountered exception while replenishing connections to {}:{}.", poolManager.host,
            poolManager.port.getPort(), e);
      }
    }
    return connectionsInitiated;
  }