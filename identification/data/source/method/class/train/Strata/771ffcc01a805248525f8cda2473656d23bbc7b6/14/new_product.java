public double parSpread(ResolvedIborFixingDeposit deposit, RatesProvider provider) {
    return forwardRate(deposit, provider) - deposit.getFixedRate();
  }