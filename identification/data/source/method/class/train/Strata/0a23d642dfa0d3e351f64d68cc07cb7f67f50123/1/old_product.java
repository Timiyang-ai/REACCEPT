public Map<CurveId, Curve> getCurves(CurveGroupName groupName) {
    // use a HashMap to avoid errors due to duplicates
    Map<CurveId, Curve> curves = new HashMap<>();
    discountCurves.values().forEach(curve -> curves.put(CurveId.of(groupName, curve.getName()), curve));
    indexCurves.values().forEach(curve -> curves.put(CurveId.of(groupName, curve.getName()), curve));
    return curves;
  }