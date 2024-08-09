public double parRate(PricingEnvironment env, SwapProduct product) {
    // find fixed leg
    ExpandedSwap swap = product.expand();
    List<ExpandedSwapLeg> legs = swap.getLegs();
    ExpandedSwapLeg fixedLeg = null;
    for(ExpandedSwapLeg leg: legs) {
      if (leg.getType() == SwapLegType.FIXED) {
        fixedLeg = leg;
        break;
      }
    }
    ArgChecker.notNull(fixedLeg, "no fixed leg found");
    @SuppressWarnings("null")
    Currency ccyFixedLeg = fixedLeg.getCurrency();
    // other payments (not fixed leg coupons) converted in fixed leg currency
    double otherLegsConvertedPv = 0.0;
    for (ExpandedSwapLeg leg: legs) {
      if (leg != fixedLeg) {
        double pvLocal = legValue(env, leg, paymentPeriodPricer::presentValue, paymentEventPricer::presentValue);
        otherLegsConvertedPv += (pvLocal * env.fxRate(leg.getCurrency(), ccyFixedLeg));
      }
    }
    double fixedLegEventsPv = fixedLeg.getPaymentEvents().stream()
        .filter(p -> !p.getPaymentDate().isBefore(env.getValuationDate()))
        .mapToDouble(e -> paymentEventPricer.presentValue(env, e))
        .sum();
    // PVBP
    double pvbpFixedLeg = pvbp(env, fixedLeg);
    // Par rate
    return -(otherLegsConvertedPv + fixedLegEventsPv) / pvbpFixedLeg;
  }