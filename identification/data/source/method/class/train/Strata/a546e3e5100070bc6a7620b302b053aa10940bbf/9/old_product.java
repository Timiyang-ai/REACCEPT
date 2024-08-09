public FxSwapTrade createTrade(
      LocalDate tradeDate,
      BuySell buySell,
      double notional,
      double nearFxRate,
      double forwardPoints) {
    return convention.createTrade(tradeDate, periodToNear, periodToFar, buySell, notional, nearFxRate, forwardPoints);
  }