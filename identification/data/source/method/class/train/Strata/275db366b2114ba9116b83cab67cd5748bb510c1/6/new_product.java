public MultiCurrencyAmount currencyExposure(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    return swaptionPricer.currencyExposure(swaption, ratesProvider, swaptionVolatilities);
  }