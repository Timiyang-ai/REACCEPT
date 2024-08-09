public double couponEquivalent(ResolvedSwapLeg leg, RatesProvider provider, double pvbp) {
    return presentValuePeriodsInternal(leg, provider) / pvbp;
  }