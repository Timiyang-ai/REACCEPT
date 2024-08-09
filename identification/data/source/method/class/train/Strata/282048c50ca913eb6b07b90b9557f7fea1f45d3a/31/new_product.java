public PointSensitivities presentValueSensitivitySabrParameter(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    return productPricer.presentValueSensitivitySabrParameter(
        trade.getProduct(), ratesProvider, swaptionVolatilities).build();
  }