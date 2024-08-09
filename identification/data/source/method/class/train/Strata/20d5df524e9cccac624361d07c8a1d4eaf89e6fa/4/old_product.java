public MultiCurrencyAmount currencyExposureFromCleanPrice(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData,
      double cleanRealPrice) {

    CurrencyAmount pv = presentValueFromCleanPrice(
        trade, ratesProvider, issuerDiscountFactorsProvider, refData, cleanRealPrice);
    return MultiCurrencyAmount.of(pv);
  }