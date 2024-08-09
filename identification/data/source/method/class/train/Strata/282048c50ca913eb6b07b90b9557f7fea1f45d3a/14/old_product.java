public double presentValueSensitivityStrike(
      CmsProduct cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    return cmsLegPricer.presentValueSensitivityStrike(cms.expand().getCmsLeg(), ratesProvider, swaptionVolatilities);
  }