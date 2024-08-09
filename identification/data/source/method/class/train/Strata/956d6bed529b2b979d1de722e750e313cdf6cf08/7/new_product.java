public MultiCurrencyAmount currencyExposureWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      LocalDate referenceDate,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    return MultiCurrencyAmount.of(presentValueWithZSpread(bond, ratesProvider, issuerDiscountFactorsProvider,
        referenceDate, zSpread, compoundedRateType, periodsPerYear));
  }