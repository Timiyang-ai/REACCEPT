public CurrencyAmount presentValue(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    return presentValue(bond, ratesProvider, issuerDiscountFactorsProvider, ratesProvider.getValuationDate());
  }