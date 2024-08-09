public ResourceBundle getDriverConfigBundle() {
    if(driverConfigBundle != null) {
      return driverConfigBundle;
    }
    retrieveAndCacheDriver();
    return driverConfigBundle;
  }