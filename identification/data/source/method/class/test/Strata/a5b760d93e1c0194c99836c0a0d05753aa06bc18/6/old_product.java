public PointSensitivityBuilder parRateSensitivity(ResolvedSwap swap, RatesProvider provider) {
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
    //does the fixed leg have a future value notional
    SwapPaymentPeriod firstPeriod = fixedLeg.getPaymentPeriods().get(0);
    if (firstPeriod instanceof RatePaymentPeriod) {
      RatePaymentPeriod payment = (RatePaymentPeriod) firstPeriod;
      RateAccrualPeriod firstAccrualPeriod = payment.getAccrualPeriods().get(0);
      if (firstAccrualPeriod.getRateComputation() instanceof FixedOvernightCompoundedAnnualRateComputation) {
        double notional = payment.getNotional();
        double df = provider.discountFactor(ccyFixedLeg, payment.getPaymentDate());
        double af = firstAccrualPeriod.getYearFraction();
        double otherLegsConvertedPvBar = -Math.pow(-otherLegsConvertedPv  / (notional * df) + 1.0d, 1.0 / af - 1.0d) / (af * notional * df);
        double dfBar = Math.pow(-otherLegsConvertedPv  / (notional * df) + 1.0d, 1.0 / af - 1.0d) * otherLegsConvertedPv / (af * notional * df * df);
        PointSensitivityBuilder otherLegsConvertedPvDr = PointSensitivityBuilder.none();
        for (ResolvedSwapLeg leg : swap.getLegs()) {
          if (leg != fixedLeg) {
            PointSensitivityBuilder pvLegDr = getLegPricer().presentValueSensitivity(leg, provider)
                .multipliedBy(provider.fxRate(leg.getCurrency(), ccyFixedLeg));
            otherLegsConvertedPvDr = otherLegsConvertedPvDr.combinedWith(pvLegDr);
          }
        }
        otherLegsConvertedPvDr = otherLegsConvertedPvDr.withCurrency(ccyFixedLeg);
        PointSensitivityBuilder dfDr = provider.discountFactors(ccyFixedLeg).zeroRatePointSensitivity(firstPeriod.getPaymentDate());
        return dfDr.multipliedBy(dfBar).combinedWith(otherLegsConvertedPvDr.multipliedBy(otherLegsConvertedPvBar));
      }
    }
    double fixedLegEventsPv = legPricer.presentValueEventsInternal(fixedLeg, provider);
    double pvbpFixedLeg = legPricer.pvbp(fixedLeg, provider);
    // Backward sweep
    double otherLegsConvertedPvBar = -1.0d / pvbpFixedLeg;
    double fixedLegEventsPvBar = -1.0d / pvbpFixedLeg;
    double pvbpFixedLegBar = (otherLegsConvertedPv + fixedLegEventsPv) / (pvbpFixedLeg * pvbpFixedLeg);
    PointSensitivityBuilder pvbpFixedLegDr = legPricer.pvbpSensitivity(fixedLeg, provider);
    PointSensitivityBuilder fixedLegEventsPvDr = legPricer.presentValueSensitivityEventsInternal(fixedLeg, provider);
    PointSensitivityBuilder otherLegsConvertedPvDr = PointSensitivityBuilder.none();
    for (ResolvedSwapLeg leg : swap.getLegs()) {
      if (leg != fixedLeg) {
        PointSensitivityBuilder pvLegDr = legPricer.presentValueSensitivity(leg, provider)
            .multipliedBy(provider.fxRate(leg.getCurrency(), ccyFixedLeg));
        otherLegsConvertedPvDr = otherLegsConvertedPvDr.combinedWith(pvLegDr);
      }
    }
    otherLegsConvertedPvDr = otherLegsConvertedPvDr.withCurrency(ccyFixedLeg);
    return pvbpFixedLegDr.multipliedBy(pvbpFixedLegBar)
        .combinedWith(fixedLegEventsPvDr.multipliedBy(fixedLegEventsPvBar))
        .combinedWith(otherLegsConvertedPvDr.multipliedBy(otherLegsConvertedPvBar));
  }