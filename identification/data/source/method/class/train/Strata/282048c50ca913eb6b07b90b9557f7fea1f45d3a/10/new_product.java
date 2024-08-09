public double presentValueSensitivityStrike(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    return productPricer.presentValueSensitivityStrike(trade.getProduct(), ratesProvider, swaptionVolatilities);
  }