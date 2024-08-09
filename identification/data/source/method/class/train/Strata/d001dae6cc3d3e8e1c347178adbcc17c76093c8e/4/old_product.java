public PointSensitivityBuilder presentValueSensitivity(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider
      issuerDiscountFactorsProvider) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    return presentValueSensitivity(
        product, ratesProvider, issuerDiscountFactorsProvider, ratesProvider.getValuationDate());
  }