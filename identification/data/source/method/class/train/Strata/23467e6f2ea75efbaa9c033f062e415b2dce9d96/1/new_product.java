public static ImmutableMap<QuoteId, Double> load(LocalDate marketDataDate, Collection<ResourceLocator> resources) {
    Collection<CharSource> charSources = resources.stream().map(r -> r.getCharSource()).collect(toList());
    return parse(d -> marketDataDate.equals(d), charSources).getOrDefault(marketDataDate, ImmutableMap.of());
  }