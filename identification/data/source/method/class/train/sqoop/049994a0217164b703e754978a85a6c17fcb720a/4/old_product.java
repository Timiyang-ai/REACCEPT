public Collection<MConnector> getConnectors() {
    if(allConnectors) {
      return connectors.values();
    }

    ConnectorBean bean = requests.readConnector(null);
    allConnectors = true;
    for(MConnector connector : bean.getConnectors()) {
      connectors.put(connector.getPersistenceId(), connector);
    }
    bundles = bean.getResourceBundles();

    return connectors.values();
  }