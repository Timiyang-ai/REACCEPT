public PointSensitivityBuilder parRateSensitivity(SwapProduct product, RatesProvider provider) {
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
    double pvbpFixedLeg = legPricer.pvbp(fixedLeg, provider);
    // Backward sweep
    double otherLegsConvertedPvBar = -1.0d / pvbpFixedLeg;
    double fixedLegEventsPvBar = -1.0d / pvbpFixedLeg;
    double pvbpFixedLegBar = (otherLegsConvertedPv + fixedLegEventsPv) / (pvbpFixedLeg * pvbpFixedLeg);
    PointSensitivityBuilder pvbpFixedLegDr = legPricer.pvbpSensitivity(fixedLeg, provider);
    PointSensitivityBuilder fixedLegEventsPvDr = legPricer.presentValueSensitivityEventsInternal(fixedLeg, provider);
    PointSensitivityBuilder otherLegsConvertedPvDr = PointSensitivityBuilder.none();
    for (ExpandedSwapLeg leg : swap.getLegs()) {
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