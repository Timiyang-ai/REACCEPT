public CashFlows cashFlows(SwapProduct product, RatesProvider provider) {
    ExpandedSwap expanded = product.expand();
    return expanded.getLegs().stream()
        .map(leg -> legPricer.cashFlows(leg, provider))
        .reduce(CashFlows.NONE, CashFlows::combinedWith);
  }