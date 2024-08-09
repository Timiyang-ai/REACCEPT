public MultiCurrencyAmount currencyExposureWithZSpread(
      CapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      double cleanRealPrice,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    return MultiCurrencyAmount.of(presentValueWithZSpread(trade, ratesProvider, issuerDiscountFactorsProvider,
        cleanRealPrice, zSpread, compoundedRateType, periodsPerYear));
  }