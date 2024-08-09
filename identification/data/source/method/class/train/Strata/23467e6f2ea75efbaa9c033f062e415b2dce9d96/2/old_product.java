public static ImmutableMap<FxRateId, FxRate> load(LocalDate marketDataDate, Collection<ResourceLocator> resources) {
    // builder ensures keys can only be seen once
    ImmutableMap.Builder<FxRateId, FxRate> builder = ImmutableMap.builder();

    for (ResourceLocator timeSeriesResource : resources) {
      loadSingle(marketDataDate, timeSeriesResource, builder);
    }
    return builder.build();
  }