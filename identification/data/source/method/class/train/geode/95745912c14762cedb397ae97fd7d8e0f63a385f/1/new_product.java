public InternalCacheBuilder setIsClient(boolean isClient) {
    this.isClient = isClient;
    metricsSessionBuilder.setIsClient(isClient);
    return this;
  }