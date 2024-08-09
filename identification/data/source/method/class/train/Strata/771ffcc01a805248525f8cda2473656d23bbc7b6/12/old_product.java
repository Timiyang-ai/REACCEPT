public double parSpread(IborFixingDepositProduct product, RatesProvider provider) {
    ExpandedIborFixingDeposit deposit = product.expand();
    return forwardRate(deposit, provider) - deposit.getRate();
  }