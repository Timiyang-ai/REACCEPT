public MultiCurrencyAmount currencyExposureWithZSpread(
      CapitalIndexedBond product,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      LocalDate referenceDate,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    return MultiCurrencyAmount.of(presentValueWithZSpread(product, ratesProvider, issuerDiscountFactorsProvider,
        referenceDate, zSpread, compoundedRateType, periodsPerYear));
  }