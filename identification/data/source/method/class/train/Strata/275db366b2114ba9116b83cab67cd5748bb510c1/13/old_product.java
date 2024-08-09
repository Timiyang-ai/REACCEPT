public MultiCurrencyAmount currencyExposure(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      SabrVolatilitySwaptionProvider volatilityProvider) {
    return MultiCurrencyAmount.of(presentValue(swaption, ratesProvider, volatilityProvider));
  }