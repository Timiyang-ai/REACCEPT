public PointSensitivityBuilder presentValueSensitivity(
      CmsTrade cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    PointSensitivityBuilder pvSensiCms =
        productPricer.presentValueSensitivity(cms.getProduct(), ratesProvider, swaptionVolatilities);
    if (!cms.getPremium().isPresent()) {
      return pvSensiCms;
    }
    PointSensitivityBuilder pvSensiPremium = paymentPricer.presentValueSensitivity(cms.getPremium().get(), ratesProvider);
    return pvSensiCms.combinedWith(pvSensiPremium);
  }