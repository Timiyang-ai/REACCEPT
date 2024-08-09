public default SwapTrade createTrade(
      LocalDate tradeDate,
      Tenor tenor,
      BuySell buySell,
      double notional,
      double fixedRate) {

    return createTrade(tradeDate, Period.ZERO, tenor, buySell, notional, fixedRate);
  }