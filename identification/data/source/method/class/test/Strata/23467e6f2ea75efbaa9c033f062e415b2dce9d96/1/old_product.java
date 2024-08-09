public static ImmutableMap<QuoteId, Double> load(LocalDate marketDataDate, Collection<ResourceLocator> resources) {
    // builder ensures keys can only be seen once
    ImmutableMap.Builder<QuoteId, Double> builder = ImmutableMap.builder();
    for (ResourceLocator timeSeriesResource : resources) {
      loadSingle(marketDataDate, timeSeriesResource, builder);
    }
    return builder.build();
  }