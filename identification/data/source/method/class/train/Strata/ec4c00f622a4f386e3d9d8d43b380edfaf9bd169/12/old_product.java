public SwapTrade toTrade(
      StandardId id,
      LocalDate tradeDate,
      Tenor tenor,
      BuySell buySell,
      double notional,
      double fixedRate) {

    return toTrade(id, tradeDate, Period.ZERO, tenor, buySell, notional, fixedRate);
  }