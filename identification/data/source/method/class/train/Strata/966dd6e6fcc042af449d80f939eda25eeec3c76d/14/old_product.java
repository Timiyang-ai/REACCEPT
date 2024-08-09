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
          double fvInf = legPricer.futureValuePeriodsInternal(leg.expand(), provider);
          RatePaymentPeriod payment = (RatePaymentPeriod) fixedLeg.getPaymentPeriods().get(0);
          double notional = payment.getNotional();
          double years = payment.getAccrualPeriods().size();
          return Math.pow(-fvInf / notional + 1.0d, 1.0d / years) - 1.0d;
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