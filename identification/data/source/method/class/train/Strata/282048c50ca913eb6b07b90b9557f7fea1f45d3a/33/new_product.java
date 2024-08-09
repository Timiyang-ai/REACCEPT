public PointSensitivityBuilder presentValueSensitivityModelParamsSabr(
      ResolvedCms cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    return cmsLegPricer.presentValueSensitivityModelParamsSabr(cms.getCmsLeg(), ratesProvider, swaptionVolatilities);
  }