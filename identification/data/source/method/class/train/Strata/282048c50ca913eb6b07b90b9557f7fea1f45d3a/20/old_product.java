public double presentValueSensitivityStrike(
      ResolvedCms cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    return cmsLegPricer.presentValueSensitivityStrike(cms.getCmsLeg(), ratesProvider, swaptionVolatilities);
  }