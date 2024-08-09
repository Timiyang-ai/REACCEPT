public PointSensitivities presentValueSensitivityRates(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    PointSensitivityBuilder pvSensiCms =
        productPricer.presentValueSensitivityRates(trade.getProduct(), ratesProvider, swaptionVolatilities);
    if (!trade.getPremium().isPresent()) {
      return pvSensiCms.build();
    }
    PointSensitivityBuilder pvSensiPremium = paymentPricer.presentValueSensitivity(trade.getPremium().get(), ratesProvider);
    return pvSensiCms.combinedWith(pvSensiPremium).build();
  }