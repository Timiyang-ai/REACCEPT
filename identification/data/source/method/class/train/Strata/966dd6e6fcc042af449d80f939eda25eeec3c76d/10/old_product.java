public double parRate(SwapProduct product, RatesProvider provider) {
    // find fixed leg
    ExpandedSwap swap = product.expand();
    ExpandedSwapLeg fixedLeg = fixedLeg(swap);
    Currency ccyFixedLeg = fixedLeg.getCurrency();
    // other payments (not fixed leg coupons) converted in fixed leg currency
    double otherLegsConvertedPv = 0.0;
    for (ExpandedSwapLeg leg : swap.getLegs()) {
      if (leg != fixedLeg) {
        double pvLocal = legPricer.presentValueInternal(leg, provider);
        otherLegsConvertedPv += (pvLocal * provider.fxRate(leg.getCurrency(), ccyFixedLeg));
      }
    }
    double fixedLegEventsPv = legPricer.presentValueEventsInternal(fixedLeg, provider);
    if (fixedLeg.getPaymentPeriods().size() > 1) { // try multiperiod par-rate
      // PVBP
      double pvbpFixedLeg = legPricer.pvbp(fixedLeg, provider);
      // Par rate
      return -(otherLegsConvertedPv + fixedLegEventsPv) / pvbpFixedLeg;
    } else {
      RatePaymentPeriod payment = (RatePaymentPeriod) fixedLeg.getPaymentPeriods().get(0);
      if (payment.getAccrualPeriods().size() == 1) { // no compounding
        // PVBP
        double pvbpFixedLeg = legPricer.pvbp(fixedLeg, provider);
        // Par rate
        return -(otherLegsConvertedPv + fixedLegEventsPv) / pvbpFixedLeg;
      }
      // try Compounding
      ImmutableList<RateAccrualPeriod> ap = payment.getAccrualPeriods();
      ArgChecker.isFalse(payment.getCompoundingMethod().equals(CompoundingMethod.NONE), "should be compounding");
      for (RateAccrualPeriod p : ap) {
        ArgChecker.isTrue(p.getYearFraction() == 1.0, "accrual factor should be 1");
        ArgChecker.isTrue(p.getSpread() == 0.0, "no spread");
      }
      double nbAp = ap.size();
      double notioanl = payment.getNotional();
      double df = provider.discountFactor(ccyFixedLeg, payment.getPaymentDate());
      return Math.pow(-(otherLegsConvertedPv + fixedLegEventsPv) / notioanl / df + 1.0d, 1 / nbAp) - 1.0d;
    }
  }