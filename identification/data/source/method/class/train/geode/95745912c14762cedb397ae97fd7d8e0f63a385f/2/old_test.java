  @Test
  public void addMeterSubregistry_addsGivenRegistryToMetricsServiceBuilder() {
    InternalCacheBuilder internalCacheBuilder = new InternalCacheBuilder(
        new Properties(), new CacheConfig(), metricsServiceBuilder, nullSingletonSystemSupplier,
        constructorOf(constructedSystem()), nullSingletonCacheSupplier,
        constructorOf(constructedCache()));

    SimpleMeterRegistry addedMeterRegistry = new SimpleMeterRegistry();

    internalCacheBuilder.addMeterSubregistry(addedMeterRegistry);

    verify(metricsServiceBuilder).addPersistentMeterRegistry(same(addedMeterRegistry));
  }