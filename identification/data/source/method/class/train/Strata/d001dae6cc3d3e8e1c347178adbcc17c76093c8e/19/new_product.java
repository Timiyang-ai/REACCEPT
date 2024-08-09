public CurrencyAmount presentValueWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    return presentValueWithZSpread(bond, ratesProvider, issuerDiscountFactorsProvider,
        ratesProvider.getValuationDate(), zSpread, compoundedRateType, periodsPerYear);
  }