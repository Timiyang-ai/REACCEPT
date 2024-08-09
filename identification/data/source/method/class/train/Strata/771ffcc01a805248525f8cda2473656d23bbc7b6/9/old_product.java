public double parSpread(IborFixingDepositProduct product, ImmutableRatesProvider provider) {
    ExpandedIborFixingDeposit deposit = product.expand();
    return forwardRate(deposit, provider) - deposit.getFixedRate();
  }