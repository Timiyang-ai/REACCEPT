public double parRate(ResolvedSwap swap, RatesProvider provider) {
    // find fixed leg
    ResolvedSwapLeg fixedLeg = fixedLeg(swap);
    Currency ccyFixedLeg = fixedLeg.getCurrency();
    // other payments (not fixed leg coupons) converted in fixed leg currency
    double otherLegsConvertedPv = 0.0;
    for (ResolvedSwapLeg leg : swap.getLegs()) {
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
    }
    SwapPaymentPeriod firstPeriod = fixedLeg.getPaymentPeriods().get(0);
    ArgChecker.isTrue(firstPeriod instanceof RatePaymentPeriod, "PaymentPeriod must be instance of RatePaymentPeriod");
    RatePaymentPeriod payment = (RatePaymentPeriod) firstPeriod;
    if (payment.getAccrualPeriods().size() == 1) {
      RateAccrualPeriod firstAccrualPeriod = payment.getAccrualPeriods().get(0);
      if (firstAccrualPeriod.getRateComputation() instanceof FixedOvernightCompoundedAnnualRateComputation) { // check for future value notional
        double accrualFactor = payment.getAccrualPeriods().get(0).getYearFraction();
        double notional = payment.getNotional();
        double df = provider.discountFactor(ccyFixedLeg, payment.getPaymentDate());
        return Math.pow(-otherLegsConvertedPv  / (notional * df) + 1.0d,   1.0 / accrualFactor) - 1.0d;
      } else { // no compounding
        // PVBP
        double pvbpFixedLeg = legPricer.pvbp(fixedLeg, provider);
        // Par rate
        return -(otherLegsConvertedPv + fixedLegEventsPv) / pvbpFixedLeg;
      }
    }
    // try Compounding
    Triple<Boolean, Integer, Double> fixedCompounded = checkFixedCompounded(fixedLeg);
    ArgChecker.isTrue(fixedCompounded.getFirst(),
        "Swap should have a fixed leg and for one payment it should be based on compunding witout spread.");
    double notional = payment.getNotional();
    double df = provider.discountFactor(ccyFixedLeg, payment.getPaymentDate());
    return Math.pow(-(otherLegsConvertedPv + fixedLegEventsPv) / (notional * df) + 1.0d,
        1.0 / fixedCompounded.getSecond()) - 1.0d;
  }