public static ImmutableList<RatesCurveGroup> load(
      LocalDate marketDataDate,
      ResourceLocator groupsResource,
      ResourceLocator settingsResource,
      Collection<ResourceLocator> curveValueResources) {

    Collection<CharSource> curveCharSources = curveValueResources.stream().map(r -> r.getCharSource()).collect(toList());
    ListMultimap<LocalDate, RatesCurveGroup> map = parse(
        d -> marketDataDate.equals(d),
        groupsResource.getCharSource(),
        settingsResource.getCharSource(),
        curveCharSources);
    return ImmutableList.copyOf(map.get(marketDataDate));
  }