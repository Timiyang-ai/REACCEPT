public CurrencyAmount presentValue(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider) {

    validate(ratesProvider, discountingProvider);
    return presentValue(bond, ratesProvider, discountingProvider, ratesProvider.getValuationDate());
  }