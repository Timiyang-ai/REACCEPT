@Deprecated
  public MultiCurrencyAmount currencyExposureWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    return currencyExposureWithZSpread(
        trade, ratesProvider, discountingProvider, zSpread, compoundedRateType, periodsPerYear);
  }