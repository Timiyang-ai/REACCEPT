public MultiCurrencyAmount currencyExposureFromCleanPriceWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData,
      double cleanRealPrice,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    CurrencyAmount pv = presentValueFromCleanPriceWithZSpread(
        trade,
        ratesProvider,
        discountingProvider,
        refData,
        cleanRealPrice,
        zSpread,
        compoundedRateType,
        periodsPerYear);
    return MultiCurrencyAmount.of(pv);
  }