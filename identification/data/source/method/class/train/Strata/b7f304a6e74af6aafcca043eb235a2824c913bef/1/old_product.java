public double parRate(IborFixingDepositProduct product, ImmutableRatesProvider provider) {
    ExpandedIborFixingDeposit deposit = product.expand();
    return forwardRate(deposit, provider);
  }