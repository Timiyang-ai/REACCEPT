public MConnector getConnector(String connectorName) {
    // Firstly try if we have this connector already in cache
    MConnector connector = getConnectorFromCache(connectorName);
    if(connector != null) return connector;

    // If the connector wasn't in cache and we have all connectors,
    // it simply do not exists.
    if(allConnectors) return null;

    // Retrieve all connectors from server
    getConnectors();
    return getConnectorFromCache(connectorName);
  }