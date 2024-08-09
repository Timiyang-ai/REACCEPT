public FraTrade toTrade(
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double fixedRate) {

    ArgChecker.inOrderOrEqual(tradeDate, startDate, "tradeDate", "startDate");
    return FraTrade.builder()
        .tradeInfo(TradeInfo.builder()
            .tradeDate(tradeDate)
            .build())
        .product(Fra.builder()
            .buySell(buySell)
            .currency(getCurrency())
            .notional(notional)
            .startDate(startDate)
            .endDate(endDate)
            .businessDayAdjustment(getBusinessDayAdjustment())
            .paymentDate(getPaymentDateOffset().toAdjustedDate(startDate))
            .fixedRate(fixedRate)
            .index(index)
            .fixingDateOffset(getFixingDateOffset())
            .dayCount(getDayCount())
            .discounting(getDiscounting())
            .build())
        .build();
  }