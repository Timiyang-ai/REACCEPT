public MultiCurrencyAmount currencyExposureWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    CurrencyAmount pv = presentValueWithZSpread(
        trade,
        ratesProvider,
        discountingProvider,
        refData,
        zSpread,
        compoundedRateType,
        periodsPerYear);
    return MultiCurrencyAmount.of(pv);
  }