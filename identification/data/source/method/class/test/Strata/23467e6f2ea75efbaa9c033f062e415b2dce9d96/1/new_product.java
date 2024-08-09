public static ImmutableMap<QuoteId, Double> load(LocalDate marketDataDate, Collection<ResourceLocator> resources) {
    // builder ensures keys can only be seen once
    Map<LocalDate, ImmutableMap.Builder<QuoteId, Double>> mutableMap = new HashMap<>();
    for (ResourceLocator resource : resources) {
      loadSingle(ImmutableSet.of(marketDataDate), resource, mutableMap);
    }
    return mutableMap.getOrDefault(marketDataDate, ImmutableMap.builder()).build();
  }