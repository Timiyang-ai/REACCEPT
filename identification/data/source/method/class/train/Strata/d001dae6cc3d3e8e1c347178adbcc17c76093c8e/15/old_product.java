public CurrencyAmount presentValue(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    return presentValue(product, ratesProvider, issuerDiscountFactorsProvider, ratesProvider.getValuationDate());
  }