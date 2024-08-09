public MultiCurrencyAmount currencyExposureWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate referenceDate,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    return MultiCurrencyAmount.of(presentValueWithZSpread(bond, ratesProvider, discountingProvider,
        referenceDate, zSpread, compoundedRateType, periodsPerYear));
  }