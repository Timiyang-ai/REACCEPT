public default SwapTrade createTrade(
      LocalDate tradeDate,
      Tenor tenor,
      BuySell buySell,
      double notional,
      double spread) {

    return createTrade(tradeDate, Period.ZERO, tenor, buySell, notional, spread);
  }