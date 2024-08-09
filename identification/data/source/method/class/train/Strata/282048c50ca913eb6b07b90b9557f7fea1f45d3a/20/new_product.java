public double presentValueSensitivityStrike(
      ResolvedCms cms,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    return cmsLegPricer.presentValueSensitivityStrike(cms.getCmsLeg(), ratesProvider, swaptionVolatilities);
  }