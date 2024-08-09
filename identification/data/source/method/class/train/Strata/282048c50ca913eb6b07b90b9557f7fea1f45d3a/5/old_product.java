public double presentValueSensitivityStrike(
      CmsTrade cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    return productPricer.presentValueSensitivityStrike(cms.getProduct(), ratesProvider, swaptionVolatilities);
  }