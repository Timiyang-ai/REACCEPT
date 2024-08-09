public SwapTrade toTrade(
      StandardId id,
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double spread) {

    ArgChecker.inOrderOrEqual(tradeDate, startDate, "tradeDate", "startDate");
    SwapLeg leg1 = spreadLeg.toLeg(startDate, endDate, PayReceive.ofPay(buySell.isBuy()), notional, spread);
    SwapLeg leg2 = flatLeg.toLeg(startDate, endDate, PayReceive.ofPay(buySell.isSell()), notional);
    return SwapTrade.builder()
        .standardId(id)
        .tradeInfo(TradeInfo.builder()
            .tradeDate(tradeDate)
            .build())
        .product(Swap.of(leg1, leg2))
        .build();
  }