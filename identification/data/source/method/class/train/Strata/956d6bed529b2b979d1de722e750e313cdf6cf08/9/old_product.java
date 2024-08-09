public CurrencyAmount presentValue(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData,
      double cleanRealPrice) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    CurrencyAmount pvProduct = productPricer.presentValue(trade.getProduct(), ratesProvider,
        issuerDiscountFactorsProvider, settlementDate);
    CurrencyAmount pvSettle = presentValueFromCleanPrice(
        trade, ratesProvider, issuerDiscountFactorsProvider, refData, cleanRealPrice);
    return pvProduct.multipliedBy(trade.getQuantity()).plus(pvSettle);
  }