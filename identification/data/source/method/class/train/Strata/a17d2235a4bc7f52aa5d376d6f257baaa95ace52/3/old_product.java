public default CdsTrade toTrade(
      StandardId legalEntityId,
      LocalDate tradeDate,
      LocalDate startDate,
      LocalDate endDate,
      BuySell buySell,
      double notional,
      double fixedRate,
      ReferenceData refData) {

    LocalDate settlementDate = getSettlementDateOffset().adjust(tradeDate, refData);
    TradeInfo tradeInfo = TradeInfo.builder()
        .tradeDate(tradeDate)
        .settlementDate(settlementDate)
        .build();
    return toTrade(legalEntityId, tradeInfo, startDate, endDate, buySell, notional, fixedRate);
  }