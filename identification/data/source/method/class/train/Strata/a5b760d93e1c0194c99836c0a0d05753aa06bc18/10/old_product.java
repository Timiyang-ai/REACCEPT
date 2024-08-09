public double couponEquivalent(SwapLeg leg, RatesProvider provider, double pvbp) {
    ExpandedSwapLeg legExpanded = leg.expand();
    return presentValuePeriodsInternal(legExpanded, provider) / pvbp;
  }