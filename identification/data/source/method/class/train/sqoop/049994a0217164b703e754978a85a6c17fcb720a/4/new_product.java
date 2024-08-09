public Collection<MConnector> getConnectors() {
    if(isAllConnectors) {
      return connectors.values();
    }

    ConnectorBean bean = resourceRequests.readConnector(null);
    isAllConnectors = true;
    for(MConnector connector : bean.getConnectors()) {
      connectors.put(connector.getPersistenceId(), connector);
    }
    connectorConfigBundles = bean.getResourceBundles();

    return connectors.values();
  }