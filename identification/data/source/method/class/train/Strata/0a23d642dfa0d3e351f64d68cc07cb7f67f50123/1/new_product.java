public Map<RatesCurveId, Curve> getCurves(CurveGroupName groupName) {
    // use a HashMap to avoid errors due to duplicates
    Map<RatesCurveId, Curve> curves = new HashMap<>();
    discountCurves.values().forEach(curve -> curves.put(RatesCurveId.of(groupName, curve.getName()), curve));
    indexCurves.values().forEach(curve -> curves.put(RatesCurveId.of(groupName, curve.getName()), curve));
    return curves;
  }