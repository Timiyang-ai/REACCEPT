public double impliedVolatility(
      SwaptionProduct swaption,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    return swaptionPricer.impliedVolatility(swaption, ratesProvider, swaptionVolatilities);
  }