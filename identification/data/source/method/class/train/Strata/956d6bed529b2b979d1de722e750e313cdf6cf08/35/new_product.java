@Deprecated
  public CurrencyAmount presentValueWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    return presentValueWithZSpread(trade, ratesProvider, discountingProvider, zSpread, compoundedRateType, periodsPerYear);
  }