public IsdaCompliantZeroRateDiscountFactors withCurve(InterpolatedNodalCurve curve) {
    return new IsdaCompliantZeroRateDiscountFactors(currency, valuationDate, curve);
  }