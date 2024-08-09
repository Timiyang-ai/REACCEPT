@Override
  public FxSwapTrade toTrade(
      TradeInfo tradeInfo,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double nearFxRate,
      double farLegForwardPoints) {

    Optional<LocalDate> tradeDate = tradeInfo.getTradeDate();
    if (tradeDate.isPresent()) {
      ArgChecker.inOrderOrEqual(tradeDate.get(), startDate, "tradeDate", "startDate");
    }
    double amount1 = BuySell.BUY.normalize(notional);
    return FxSwapTrade.builder()
        .info(tradeInfo)
        .product(FxSwap.ofForwardPoints(
            CurrencyAmount.of(currencyPair.getBase(), amount1),
            FxRate.of(currencyPair, nearFxRate),
            farLegForwardPoints,
            startDate,
            endDate,
            getBusinessDayAdjustment()))
        .build();
  }