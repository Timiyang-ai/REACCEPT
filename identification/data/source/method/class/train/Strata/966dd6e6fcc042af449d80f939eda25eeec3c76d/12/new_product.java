public double parRate(SwapProduct product, RatesProvider provider) {
    // find fixed leg
    ExpandedSwap swap = product.expand();
    ExpandedSwapLeg fixedLeg = fixedLeg(swap);
    Currency ccyFixedLeg = fixedLeg.getCurrency();
    // other payments (not fixed leg coupons) converted in fixed leg currency
    double otherLegsConvertedPv = 0.0;
    for (ExpandedSwapLeg leg : swap.getLegs()) {
      if (leg != fixedLeg) {
        if (leg.getType().equals(SwapLegType.INFLATION)) { // handle inflation swap
          double pvInfLocal = legPricer.presentValuePeriodsInternal(leg.expand(), provider);
          double notional = ((RatePaymentPeriod) leg.getPaymentPeriods().get(0)).getNotional();
          double years = fixedLeg.getPaymentPeriods().size();
          return Math.pow(pvInfLocal / notional + 1.0d, 1.0d / years) - 1.0d;
        }
        double pvLocal = legPricer.presentValueInternal(leg, provider);
        otherLegsConvertedPv += (pvLocal * provider.fxRate(leg.getCurrency(), ccyFixedLeg));
      }
    }
    double fixedLegEventsPv = legPricer.presentValueEventsInternal(fixedLeg, provider);
    // PVBP
    double pvbpFixedLeg = legPricer.pvbp(fixedLeg, provider);
    // Par rate
    return -(otherLegsConvertedPv + fixedLegEventsPv) / pvbpFixedLeg;
  }