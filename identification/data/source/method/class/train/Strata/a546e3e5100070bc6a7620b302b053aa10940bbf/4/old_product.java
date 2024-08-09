public SwapTrade createTrade(LocalDate tradeDate, BuySell buySell, double notional, double fixedRate) {
    return convention.createTrade(tradeDate, periodToStart, tenor, buySell, notional, fixedRate);
  }