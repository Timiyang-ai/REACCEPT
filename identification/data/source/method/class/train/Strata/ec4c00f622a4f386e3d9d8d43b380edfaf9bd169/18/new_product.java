public default SwapTrade toTrade(
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double spread) {
    
    TradeInfo tradeInfo = TradeInfo.of(tradeDate);
    return toTrade(tradeInfo, startDate, endDate, buySell, notional, spread);
  }