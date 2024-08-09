public double parSpread(SwapProduct product, RatesProvider provider) {
    ExpandedSwap swap = product.expand();
    SwapLeg referenceLeg = swap.getLegs().get(0);
    Currency ccyReferenceLeg = referenceLeg.getCurrency();
    double convertedPv = presentValue(swap, ccyReferenceLeg, provider).getAmount();
    double pvbp = legPricer.pvbp(referenceLeg, provider);
    return -convertedPv / pvbp;
  }