public MultiCurrencyAmount currencyExposure(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData) {

    CurrencyAmount pv = presentValue(trade, ratesProvider, discountingProvider, refData);
    return MultiCurrencyAmount.of(pv);
  }