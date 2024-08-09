public static List<CurveGroup> load(
      LocalDate marketDataDate,
      ResourceLocator groupsResource,
      ResourceLocator settingsResource,
      Collection<ResourceLocator> curvesResources) {

    List<CurveGroupDefinition> curveGroups = CurveGroupDefinitionCsvLoader.loadCurveGroups(groupsResource);
    Multimap<LocalDate, Curve> allCurves = loadCurves(settingsResource, curvesResources, marketDataDate);
    Collection<Curve> curves = allCurves.get(marketDataDate);
    return curveGroups.stream().map(groupDef -> CurveGroup.ofCurves(groupDef, curves)).collect(toImmutableList());
  }