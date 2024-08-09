public SwapTrade toTrade(
      StandardId id,
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double fixedRate) {

    ArgChecker.inOrderOrEqual(tradeDate, startDate, "tradeDate", "startDate");
    SwapLeg leg1 = fixedLeg.toLeg(startDate, endDate, PayReceive.ofPay(buySell.isBuy()), notional, fixedRate);
    SwapLeg leg2 = floatingLeg.toLeg(startDate, endDate, PayReceive.ofPay(buySell.isSell()), notional);
    return SwapTrade.builder()
        .standardId(id)
        .tradeInfo(TradeInfo.builder()
            .tradeDate(tradeDate)
            .build())
        .product(Swap.of(leg1, leg2))
        .build();
  }