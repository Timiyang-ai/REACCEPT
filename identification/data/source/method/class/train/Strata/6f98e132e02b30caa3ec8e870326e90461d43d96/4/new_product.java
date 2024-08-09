@Override
  public FxSwapTrade toTrade(
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double nearFxRate,
      double farLegForwardPoints) {

    ArgChecker.inOrderOrEqual(tradeDate, startDate, "tradeDate", "startDate");
    double amount1 = BuySell.BUY.normalize(notional);
    return FxSwapTrade.builder()
        .tradeInfo(TradeInfo.builder()
            .tradeDate(tradeDate).build())
        .product(FxSwap.ofForwardPoints(
            CurrencyAmount.of(currencyPair.getBase(), amount1),
            FxRate.of(currencyPair, nearFxRate),
            farLegForwardPoints,
            startDate,
            endDate,
            getBusinessDayAdjustment()))
        .build();
  }