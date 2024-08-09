public JumpToDefault jumpToDefault(
      ResolvedCdsTrade trade,
      CreditRatesProvider ratesProvider,
      ReferenceData refData) {

    LocalDate settlementDate = calculateSettlementDate(trade, ratesProvider, refData);
    return productPricer.jumpToDefault(trade.getProduct(), ratesProvider, settlementDate, refData);
  }