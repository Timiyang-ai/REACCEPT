public default SwapTrade toTrade(
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      Period lag,
      BuySell buySell,
      double notional,
      double fixedRate) {

    TradeInfo tradeInfo = TradeInfo.of(tradeDate);
    return toTrade(tradeInfo, startDate, endDate, lag, buySell, notional, fixedRate);
  }