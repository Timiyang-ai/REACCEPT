public FraTrade createTrade(
      LocalDate tradeDate,
      BuySell buySell,
      double notional,
      double fixedRate,
      ReferenceData refData) {

    return convention.createTrade(tradeDate, periodToStart, periodToEnd, buySell, notional, fixedRate, refData);
  }