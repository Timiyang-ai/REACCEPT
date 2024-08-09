public PointSensitivityBuilder presentValueSensitivitySabrParameter(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    return productPricer.presentValueSensitivitySabrParameter(trade.getProduct(), ratesProvider, swaptionVolatilities);
  }