public CashFlows cashFlows(SwapProduct product, RatesProvider provider) {
    ExpandedSwap swap = product.expand();
    return swap.getLegs().stream()
        .map(leg -> legPricer.cashFlows(leg, provider))
        .reduce(CashFlows.NONE, CashFlows::combinedWith);
  }