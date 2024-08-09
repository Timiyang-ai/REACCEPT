  @Test
  public void setIsClient_setsIsClientInMetricsServiceBuilder() {
    MetricsService.Builder theMetricsServiceBuilder = mock(MetricsService.Builder.class);

    InternalCacheBuilder internalCacheBuilder = new InternalCacheBuilder(
        new Properties(), new CacheConfig(), theMetricsServiceBuilder, nullSingletonSystemSupplier,
        constructorOf(constructedSystem()), nullSingletonCacheSupplier,
        constructorOf(constructedCache()));

    internalCacheBuilder.setIsClient(true);

    verify(theMetricsServiceBuilder).setIsClient(true);
  }