public double parSpread(ResolvedTermDeposit deposit, RatesProvider provider) {
    double parRate = parRate(deposit, provider);
    return parRate - deposit.getRate();
  }