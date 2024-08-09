public IsdaCompliantZeroRateDiscountFactors withCurve(NodalCurve curve) {
    return IsdaCompliantZeroRateDiscountFactors.of(currency, valuationDate, curve);
  }