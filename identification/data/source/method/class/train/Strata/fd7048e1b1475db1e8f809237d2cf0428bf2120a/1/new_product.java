public ZeroRateDiscountFactors withCurve(Curve curve) {
    return new ZeroRateDiscountFactors(currency, valuationDate, curve);
  }