public MultiCurrencyAmount currencyExposure(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      LocalDate referenceDate) {

    return MultiCurrencyAmount.of(presentValue(product, ratesProvider, issuerDiscountFactorsProvider, referenceDate));
  }