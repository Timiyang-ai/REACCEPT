public PointSensitivityBuilder parSpreadSensitivity(SwapProduct product, RatesProvider provider) {
    ExpandedSwap swap = product.expand();
    SwapLeg referenceLeg = swap.getLegs().get(0);
    Currency ccyReferenceLeg = referenceLeg.getCurrency();
    double convertedPv = presentValue(swap, ccyReferenceLeg, provider).getAmount();
    double pvbp = legPricer.pvbp(referenceLeg, provider);
    // Backward sweep
    double convertedPvBar = -1d / pvbp;
    double pvbpBar = convertedPv / (pvbp * pvbp);
    PointSensitivityBuilder pvbpDr = legPricer.pvbpSensitivity(referenceLeg, provider);
    PointSensitivityBuilder convertedPvDr = presentValueSensitivity(swap, ccyReferenceLeg, provider);
    return convertedPvDr.multipliedBy(convertedPvBar).combinedWith(pvbpDr.multipliedBy(pvbpBar));
  }