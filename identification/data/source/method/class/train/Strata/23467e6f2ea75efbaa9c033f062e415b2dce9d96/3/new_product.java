public static ImmutableMap<LocalDate, ImmutableMap<QuoteId, Double>> load(
      Set<LocalDate> marketDataDates,
      Collection<ResourceLocator> resources) {

    Collection<CharSource> charSources = resources.stream().map(r -> r.getCharSource()).collect(toList());
    return parse(d -> marketDataDates.contains(d), charSources);
  }