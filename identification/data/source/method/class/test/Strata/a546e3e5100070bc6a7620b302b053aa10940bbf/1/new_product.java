public IborFixingDepositTrade createTrade(
      LocalDate tradeDate,
      BuySell buySell,
      double notional,
      double fixedRate,
      ReferenceData refData) {

    return convention.createTrade(tradeDate, depositPeriod, buySell, notional, fixedRate, refData);
  }