public PointSensitivityBuilder presentValueSensitivity(
      ResolvedCms cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    PointSensitivityBuilder pvSensiCmsLeg =
        cmsLegPricer.presentValueSensitivity(cms.getCmsLeg(), ratesProvider, swaptionVolatilities);
    if (!cms.getPayLeg().isPresent()) {
      return pvSensiCmsLeg;
    }
    PointSensitivityBuilder pvSensiPayLeg = payLegPricer.presentValueSensitivity(cms.getPayLeg().get(), ratesProvider);
    return pvSensiCmsLeg.combinedWith(pvSensiPayLeg);
  }