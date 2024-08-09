public double parRate(ResolvedIborFixingDeposit deposit, RatesProvider provider) {
    return forwardRate(deposit, provider);
  }