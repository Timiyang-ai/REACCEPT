public MultiCurrencyAmount currencyExposureFromCleanPrice(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData,
      double cleanRealPrice) {

    CurrencyAmount pv = presentValueFromCleanPrice(
        trade, ratesProvider, discountingProvider, refData, cleanRealPrice);
    return MultiCurrencyAmount.of(pv);
  }