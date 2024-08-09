public TermDepositTrade createTrade(LocalDate tradeDate, BuySell buySell, double notional, double rate) {
    return convention.createTrade(tradeDate, depositPeriod, buySell, notional, rate);
  }