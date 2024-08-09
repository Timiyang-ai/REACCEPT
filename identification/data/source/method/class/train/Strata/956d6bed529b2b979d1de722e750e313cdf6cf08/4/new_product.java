public MultiCurrencyAmount currencyExposure(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate referenceDate) {

    return MultiCurrencyAmount.of(presentValue(bond, ratesProvider, discountingProvider, referenceDate));
  }