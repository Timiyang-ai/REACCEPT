public static ImmutableMap<LocalDate, ImmutableMap<QuoteId, Double>> load(
      Set<LocalDate> marketDataDates,
      Collection<ResourceLocator> resources) {

    // builder ensures keys can only be seen once
    Map<LocalDate, ImmutableMap.Builder<QuoteId, Double>> mutableMap = new HashMap<>();
    for (ResourceLocator timeSeriesResource : resources) {
      loadSingle(marketDataDates, timeSeriesResource, mutableMap);
    }
    ImmutableMap.Builder<LocalDate, ImmutableMap<QuoteId, Double>> builder = ImmutableMap.builder();
    for (Entry<LocalDate, Builder<QuoteId, Double>> entry : mutableMap.entrySet()) {
      builder.put(entry.getKey(), entry.getValue().build());
    }
    return builder.build();
  }