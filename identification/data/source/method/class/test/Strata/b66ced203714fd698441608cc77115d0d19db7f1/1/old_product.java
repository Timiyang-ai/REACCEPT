public double parSpread(TermDepositProduct product, RatesProvider provider) {
    ExpandedTermDeposit deposit = product.expand();
    double parRate = parRate(product, provider);
    return parRate - deposit.getRate();
  }