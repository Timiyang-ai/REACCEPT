public double parSpread(ResolvedSwap swap, RatesProvider provider) {
    ResolvedSwapLeg referenceLeg = swap.getLegs().get(0);
    Currency ccyReferenceLeg = referenceLeg.getCurrency();
    double convertedPv = presentValue(swap, ccyReferenceLeg, provider).getAmount();
    double pvbp = legPricer.pvbp(referenceLeg, provider);
    return -convertedPv / pvbp;
  }