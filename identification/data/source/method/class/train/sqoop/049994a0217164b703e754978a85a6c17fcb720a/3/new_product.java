public MDriverConfig getDriverConfig() {
    if(driverConfig != null) {
      return driverConfig.clone(false);
    }
    retrieveAndCacheDriverConfig();
    return driverConfig.clone(false);

  }