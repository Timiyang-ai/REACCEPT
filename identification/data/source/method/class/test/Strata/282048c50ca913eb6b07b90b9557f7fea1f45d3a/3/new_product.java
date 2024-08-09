public PointSensitivityBuilder presentValueSensitivitySabrParameter(
      ResolvedCms cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    return cmsLegPricer.presentValueSensitivitySabrParameter(cms.getCmsLeg(), ratesProvider, swaptionVolatilities);
  }