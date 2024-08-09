public default SwapTrade toTrade(
      LocalDate tradeDate,
      Tenor tenor,
      BuySell buySell,
      double notional,
      double spread) {

    return toTrade(tradeDate, Period.ZERO, tenor, buySell, notional, spread);
  }