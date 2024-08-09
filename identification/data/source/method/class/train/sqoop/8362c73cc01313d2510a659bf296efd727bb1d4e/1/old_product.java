public ResourceBundle getDriverConfigBundle() {
    if(driverConfigBundle != null) {
      return driverConfigBundle;
    }
    retrieveAndCacheDriverConfig();
    return driverConfigBundle;
  }