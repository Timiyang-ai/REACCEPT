public default SwapTrade toTrade(
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notionalSpreadLeg,
      double notionalFlatLeg,
      double spread) {
    
    TradeInfo tradeInfo = TradeInfo.of(tradeDate);
    return toTrade(tradeInfo, startDate, endDate, buySell, notionalSpreadLeg, notionalFlatLeg, spread);
  }