public PointSensitivityBuilder presentValueSensitivityWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, discountingProvider);
    return presentValueSensitivityWithZSpread(bond, ratesProvider, discountingProvider,
        ratesProvider.getValuationDate(), zSpread, compoundedRateType, periodsPerYear);
  }