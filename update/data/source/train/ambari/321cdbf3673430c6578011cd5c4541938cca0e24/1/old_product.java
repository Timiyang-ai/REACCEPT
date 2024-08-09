@Override
  protected void executeDMLUpdates() throws AmbariException, SQLException {
    // remove NAGIOS to make way for the new embedded alert framework
    removeNagiosService();
  }