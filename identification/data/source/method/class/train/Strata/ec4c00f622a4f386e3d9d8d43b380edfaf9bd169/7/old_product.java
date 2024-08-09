public FraTrade toTrade(
      StandardId id,
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double fixedRate) {

    ArgChecker.inOrderOrEqual(tradeDate, startDate, "tradeDate", "startDate");
    return FraTrade.builder()
        .standardId(id)
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
            .paymentDateOffset(getPaymentDateOffset() != DaysAdjustment.NONE ? getPaymentDateOffset() : null)
            .fixedRate(fixedRate)
            .index(index)
            .fixingDateOffset(getFixingDateOffset())
            .dayCount(getDayCount())
            .discounting(getDiscounting())
            .build())
        .build();
  }