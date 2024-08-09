public SwaptionSabrSensitivities presentValueSensitivitySabrParameter(
      CmsTrade cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    return productPricer.presentValueSensitivitySabrParameter(cms.getProduct(), ratesProvider, swaptionVolatilities);
  }