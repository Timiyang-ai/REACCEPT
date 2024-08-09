public SwapTrade toTrade(
      LocalDate tradeDate,
      Tenor tenor,
      BuySell buySell,
      double notional,
      double fixedRate) {

    return toTrade(tradeDate, Period.ZERO, tenor, buySell, notional, fixedRate);
  }