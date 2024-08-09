public CurrencyAmount presentValueWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, discountingProvider);
    return presentValueWithZSpread(bond, ratesProvider, discountingProvider,
        ratesProvider.getValuationDate(), zSpread, compoundedRateType, periodsPerYear);
  }