public InternalCacheBuilder addMeterSubregistry(MeterRegistry subregistry) {
    meterSubregistries.add(subregistry);
    return this;
  }