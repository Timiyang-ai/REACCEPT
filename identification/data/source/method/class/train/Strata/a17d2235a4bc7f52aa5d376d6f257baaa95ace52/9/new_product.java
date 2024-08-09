public default CdsTrade createTrade(
      StandardId legalEntityId,
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double fixedRate,
      AdjustablePayment upFrontFee,
      ReferenceData refData) {

    LocalDate settlementDate = getSettlementDateOffset().adjust(tradeDate, refData);
    TradeInfo tradeInfo = TradeInfo.builder()
        .tradeDate(tradeDate)
        .settlementDate(settlementDate)
        .build();
    return toTrade(legalEntityId, tradeInfo, startDate, endDate, buySell, notional, fixedRate, upFrontFee);
  }