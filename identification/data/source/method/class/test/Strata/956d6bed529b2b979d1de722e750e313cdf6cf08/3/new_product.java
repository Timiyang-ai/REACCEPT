public CurrencyAmount presentValueWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, discountingProvider);
    LocalDate settlementDate = trade.getSettlementDate();
    CurrencyAmount pvProduct = productPricer.presentValueWithZSpread(
        trade.getProduct(), ratesProvider,
        discountingProvider,
        settlementDate,
        zSpread,
        compoundedRateType,
        periodsPerYear);
    return presentValueFromProductPresentValue(trade, ratesProvider, discountingProvider, pvProduct);
  }