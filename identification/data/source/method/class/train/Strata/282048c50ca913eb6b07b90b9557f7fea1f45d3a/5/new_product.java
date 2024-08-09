public double presentValueSensitivityStrike(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    return productPricer.presentValueSensitivityStrike(trade.getProduct(), ratesProvider, swaptionVolatilities);
  }