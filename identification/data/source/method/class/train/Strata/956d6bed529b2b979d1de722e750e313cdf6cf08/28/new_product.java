public MultiCurrencyAmount currencyExposure(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData,
      double cleanRealPrice) {

    CurrencyAmount pv = presentValue(trade, ratesProvider, issuerDiscountFactorsProvider, refData, cleanRealPrice);
    return MultiCurrencyAmount.of(pv);
  }