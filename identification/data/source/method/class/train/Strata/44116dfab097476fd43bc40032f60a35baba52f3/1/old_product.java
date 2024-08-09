public SwapTrade createTrade(
      LocalDate tradeDate,
      Tenor tenor,
      Period lag,
      BuySell buySell,
      double notional,
      double fixedRate,
      ReferenceData refData) {

    return convention.createTrade(tradeDate, tenor, lag, buySell, notional, fixedRate, refData);
  }