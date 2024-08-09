@Override
  protected void executeDMLUpdates() throws AmbariException, SQLException {
    // remove NAGIOS to make way for the new embedded alert framework
    removeNagiosService();
    addNewConfigurationsFromXml();
    updateHiveDatabaseType();
    updateTezConfiguration();
    addMissingConfigs();
    persistHDPRepo();
    updateClusterEnvConfiguration();
  }