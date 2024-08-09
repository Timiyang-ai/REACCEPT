public TermDepositTrade createTrade(
      LocalDate tradeDate,
      BuySell buySell,
      double notional,
      double rate,
      ReferenceData refData) {

    return convention.createTrade(tradeDate, depositPeriod, buySell, notional, rate, refData);
  }