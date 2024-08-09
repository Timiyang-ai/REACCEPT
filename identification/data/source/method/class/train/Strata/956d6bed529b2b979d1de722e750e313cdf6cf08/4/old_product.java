public MultiCurrencyAmount currencyExposure(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      LocalDate referenceDate) {

    return MultiCurrencyAmount.of(presentValue(bond, ratesProvider, issuerDiscountFactorsProvider, referenceDate));
  }