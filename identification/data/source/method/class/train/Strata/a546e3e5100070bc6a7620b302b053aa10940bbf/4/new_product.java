public SwapTrade createTrade(
      LocalDate tradeDate,
      BuySell buySell,
      double notional,
      double fixedRate,
      ReferenceData refData) {

    return convention.createTrade(tradeDate, periodToStart, tenor, buySell, notional, fixedRate, refData);
  }