public MultiCurrencyAmount currencyExposure(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData) {

    CurrencyAmount pv = presentValue(trade, ratesProvider, issuerDiscountFactorsProvider, refData);
    return MultiCurrencyAmount.of(pv);
  }