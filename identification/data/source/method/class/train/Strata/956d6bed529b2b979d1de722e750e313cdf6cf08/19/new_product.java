public CurrencyAmount presentValueWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = trade.getInfo().getSettlementDate().get();
    CurrencyAmount pvProduct = productPricer.presentValueWithZSpread(
        trade.getProduct(), ratesProvider,
        issuerDiscountFactorsProvider,
        settlementDate,
        zSpread,
        compoundedRateType,
        periodsPerYear);
    return presentValueFromProductPresentValue(trade, ratesProvider, issuerDiscountFactorsProvider, pvProduct);
  }