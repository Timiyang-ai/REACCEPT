public PointSensitivityBuilder presentValueSensitivity(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider
      issuerDiscountFactorsProvider) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    return presentValueSensitivity(
        bond, ratesProvider, issuerDiscountFactorsProvider, ratesProvider.getValuationDate());
  }