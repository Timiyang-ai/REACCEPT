public PointSensitivityBuilder presentValueSensitivityWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    return presentValueSensitivityWithZSpread(bond, ratesProvider, issuerDiscountFactorsProvider,
        ratesProvider.getValuationDate(), zSpread, compoundedRateType, periodsPerYear);
  }