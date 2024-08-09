public IborFixingDepositTrade createTrade(LocalDate tradeDate, BuySell buySell, double notional, double fixedRate) {
    return convention.createTrade(tradeDate, depositPeriod, buySell, notional, fixedRate);
  }