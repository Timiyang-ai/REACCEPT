public double parRate(IborFixingDepositProduct product, RatesProvider provider) {
    ExpandedIborFixingDeposit deposit = product.expand();
    return forwardRate(deposit, provider);
  }