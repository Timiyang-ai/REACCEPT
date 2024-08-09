public static ImmutableMap<RateCurveId, Curve> load(
      LocalDate marketDataDate,
      ResourceLocator groupsResource,
      ResourceLocator settingsResource,
      Collection<ResourceLocator> curvesResources) {

    ImmutableMap<LocalDate, Map<RateCurveId, Curve>> curves =
        loadCurves(groupsResource, settingsResource, curvesResources, marketDataDate);
    return ImmutableMap.copyOf(curves.getOrDefault(marketDataDate, ImmutableMap.of()));
  }