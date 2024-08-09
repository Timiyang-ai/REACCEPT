public InternalCacheBuilder addMeterSubregistry(MeterRegistry subregistry) {
    requireNonNull(subregistry, "meter registry");
    metricsSessionBuilder.addPersistentMeterRegistry(subregistry);
    return this;
  }