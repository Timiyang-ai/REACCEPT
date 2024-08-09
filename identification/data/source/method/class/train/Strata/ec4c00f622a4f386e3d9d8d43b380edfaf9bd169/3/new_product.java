public default FraTrade toTrade(
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      LocalDate paymentDate,
      BuySell buySell,
      double notional,
      double fixedRate) {
    
    TradeInfo tradeInfo = TradeInfo.of(tradeDate);
    return toTrade(tradeInfo, startDate, endDate, paymentDate, buySell, notional, fixedRate);
  }