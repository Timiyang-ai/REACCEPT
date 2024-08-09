public MultiCurrencyAmount currencyExposure(
      CapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      double cleanRealPrice) {

    return MultiCurrencyAmount.of(presentValue(trade, ratesProvider, issuerDiscountFactorsProvider, cleanRealPrice));
  }