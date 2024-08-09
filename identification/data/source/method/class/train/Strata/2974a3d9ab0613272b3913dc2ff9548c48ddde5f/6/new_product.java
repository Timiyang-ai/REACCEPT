public CashFlows cashFlows(ResolvedSwap swap, RatesProvider provider) {
    return swap.getLegs().stream()
        .map(leg -> legPricer.cashFlows(leg, provider))
        .reduce(CashFlows.NONE, CashFlows::combinedWith);
  }