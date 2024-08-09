public MLink createLink(long connectorId) {
    return new MLink(
      connectorId,
      getConnector(connectorId).getConnectionForms(),
      getDriverConfig().getConnectionForms()
    );
  }