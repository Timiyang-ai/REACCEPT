@Override
  public IsdaCompliantZeroRateDiscountFactors withCurve(Curve curve) {
    ArgChecker.isTrue(curve instanceof InterpolatedNodalCurve, "curve must be InterpolatedNodalCurve");
    InterpolatedNodalCurve nodalCurve = (InterpolatedNodalCurve) curve;
    return IsdaCompliantZeroRateDiscountFactors.of(currency, valuationDate, nodalCurve);
  }