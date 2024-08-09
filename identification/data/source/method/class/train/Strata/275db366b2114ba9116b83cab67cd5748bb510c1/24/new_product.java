public CurrencyAmount presentValue(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    return swaptionPricer.presentValue(swaption, ratesProvider, swaptionVolatilities);
  }