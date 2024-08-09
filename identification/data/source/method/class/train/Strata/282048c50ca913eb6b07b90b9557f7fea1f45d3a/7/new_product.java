public PointSensitivities presentValueSensitivityModelParamsSabr(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    return productPricer.presentValueSensitivityModelParamsSabr(
        trade.getProduct(), ratesProvider, swaptionVolatilities).build();
  }