public PointSensitivityBuilder presentValueSensitivity(
      ResolvedCmsTrade trade,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    PointSensitivityBuilder pvSensiCms =
        productPricer.presentValueSensitivity(trade.getProduct(), ratesProvider, swaptionVolatilities);
    if (!trade.getPremium().isPresent()) {
      return pvSensiCms;
    }
    PointSensitivityBuilder pvSensiPremium = paymentPricer.presentValueSensitivity(trade.getPremium().get(), ratesProvider);
    return pvSensiCms.combinedWith(pvSensiPremium);
  }