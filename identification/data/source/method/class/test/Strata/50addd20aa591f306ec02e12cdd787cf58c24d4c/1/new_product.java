public static List<CurveGroup> load(
      LocalDate marketDataDate,
      ResourceLocator groupsResource,
      ResourceLocator settingsResource,
      Collection<ResourceLocator> curvesResources) {

    List<CurveGroupDefinition> curveGroups = CurveGroupDefinitionCsvLoader.loadCurveGroups(groupsResource);
    Multimap<LocalDate, Curve> allCurves = loadCurves(settingsResource, curvesResources, marketDataDate);
    Collection<Curve> curves = allCurves.get(marketDataDate);
    Set<CurveName> curveNames = new HashSet<>();

    // Ensure curve names are unique
    for (Curve curve : curves) {
      if (!curveNames.add(curve.getName())) {
        throw new IllegalArgumentException("Multiple curves with the same name: " + curve.getName());
      }
    }
    return curveGroups.stream().map(groupDef -> CurveGroup.ofCurves(groupDef, curves)).collect(toImmutableList());
  }