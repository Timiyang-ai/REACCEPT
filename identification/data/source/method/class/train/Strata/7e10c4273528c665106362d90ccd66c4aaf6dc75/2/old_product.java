@Override
  public IsdaCompliantZeroRateDiscountFactors withCurve(Curve curve) {
    ArgChecker.isTrue(curve instanceof NodalCurve, "curve must be NodalCurve");
    NodalCurve nodalCurve = (NodalCurve) curve;
    return IsdaCompliantZeroRateDiscountFactors.of(currency, valuationDate, nodalCurve);
  }