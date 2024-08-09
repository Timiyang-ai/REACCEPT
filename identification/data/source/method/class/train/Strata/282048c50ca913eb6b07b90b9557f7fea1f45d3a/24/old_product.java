public SwaptionSabrSensitivities presentValueSensitivitySabrParameter(
      CmsProduct cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    return cmsLegPricer.presentValueSensitivitySabrParameter(cms.expand().getCmsLeg(), ratesProvider, swaptionVolatilities);
  }