public SwapTrade createTrade(LocalDate tradeDate, BuySell buySell, double notional, double spread) {
    return convention.createTrade(tradeDate, periodToStart, tenor, buySell, notional, spread);
  }