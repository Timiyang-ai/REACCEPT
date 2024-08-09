@Override
  public FxSwapTrade toTrade(
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double nearFxRate,
      double forwardPoints) {

    ArgChecker.inOrderOrEqual(tradeDate, startDate, "tradeDate", "startDate");
    double amount1 = BuySell.BUY.normalize(notional);
    LocalDate startDateAdjusted = getBusinessDayAdjustment().adjust(startDate);
    LocalDate endDateAdjusted = getBusinessDayAdjustment().adjust(endDate);
    return FxSwapTrade.builder()
        .tradeInfo(TradeInfo.builder()
            .tradeDate(tradeDate).build())
        .product(FxSwap.ofForwardPoints(
            CurrencyAmount.of(currencyPair.getBase(), amount1),
            currencyPair.getCounter(),
            nearFxRate,
            forwardPoints,
            startDateAdjusted,
            endDateAdjusted))
        .build();
  }