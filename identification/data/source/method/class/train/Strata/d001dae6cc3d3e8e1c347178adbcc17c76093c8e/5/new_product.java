public PointSensitivityBuilder presentValueSensitivity(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider) {

    validate(ratesProvider, discountingProvider);
    return presentValueSensitivity(
        bond, ratesProvider, discountingProvider, ratesProvider.getValuationDate());
  }