public JumpToDefault jumpToDefault(
      ResolvedCdsIndexTrade trade,
      CreditRatesProvider ratesProvider,
      ReferenceData refData) {

    LocalDate settlementDate = calculateSettlementDate(trade, ratesProvider, refData);
    return productPricer.jumpToDefault(trade.getProduct(), ratesProvider, settlementDate, refData);
  }