public double parRate(PricingEnvironment env, SwapProduct product) {
    // find fixed leg
    ExpandedSwap swap = product.expand();
    List<ExpandedSwapLeg> fixedLegs = swap.getLegs(SwapLegType.FIXED);
    if (fixedLegs.isEmpty()) {
      throw new IllegalArgumentException("Swap must contain a fixed leg");
    }
    ExpandedSwapLeg fixedLeg = fixedLegs.get(0);
    Currency ccyFixedLeg = fixedLeg.getCurrency();
    // other payments (not fixed leg coupons) converted in fixed leg currency
    double otherLegsConvertedPv = 0.0;
    for (ExpandedSwapLeg leg : swap.getLegs()) {
      if (leg != fixedLeg) {
        double pvLocal = legPricer.presentValueInternal(env, leg);
        otherLegsConvertedPv += (pvLocal * env.fxRate(leg.getCurrency(), ccyFixedLeg));
      }
    }
    double fixedLegEventsPv = legPricer.presentValueEventsInternal(env, fixedLeg);
    // PVBP
    double pvbpFixedLeg = legPricer.pvbp(env, fixedLeg);
    // Par rate
    return -(otherLegsConvertedPv + fixedLegEventsPv) / pvbpFixedLeg;
  }