public MultiCurrencyAmount currencyExposure(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      BlackVolatilitySwaptionProvider volatilityProvider) {
    return MultiCurrencyAmount.of(presentValue(swaption, ratesProvider, volatilityProvider));
  }