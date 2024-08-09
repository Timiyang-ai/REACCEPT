public CurrencyAmount presentValueWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, discountingProvider);
    LocalDate settlementDate = settlementDate(trade, ratesProvider.getValuationDate());
    CurrencyAmount pvProduct = productPricer.presentValueWithZSpread(
        trade.getProduct(), ratesProvider,
        discountingProvider,
        settlementDate,
        zSpread,
        compoundedRateType,
        periodsPerYear);
    return presentValueFromProductPresentValue(trade, ratesProvider, discountingProvider, pvProduct);
  }