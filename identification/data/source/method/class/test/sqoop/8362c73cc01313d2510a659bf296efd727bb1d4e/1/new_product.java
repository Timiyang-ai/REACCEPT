public MDriverConfig getDriverConfig() {
    if (mDriver != null) {
      return mDriver.clone(false).getDriverConfig();
    }
    retrieveAndCacheDriver();
    return mDriver.clone(false).getDriverConfig();
  }