public SwapTrade createTrade(
      LocalDate tradeDate,
      BuySell buySell,
      double notional,
      double spread,
      ReferenceData refData) {

    return convention.createTrade(tradeDate, periodToStart, tenor, buySell, notional, spread, refData);
  }