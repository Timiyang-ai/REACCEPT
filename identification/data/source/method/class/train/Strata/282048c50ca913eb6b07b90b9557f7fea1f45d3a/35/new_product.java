public PointSensitivityBuilder presentValueSensitivityRates(
      ResolvedCms cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    PointSensitivityBuilder pvSensiCmsLeg =
        cmsLegPricer.presentValueSensitivityRates(cms.getCmsLeg(), ratesProvider, swaptionVolatilities);
    if (!cms.getPayLeg().isPresent()) {
      return pvSensiCmsLeg;
    }
    PointSensitivityBuilder pvSensiPayLeg = payLegPricer.presentValueSensitivity(cms.getPayLeg().get(), ratesProvider);
    return pvSensiCmsLeg.combinedWith(pvSensiPayLeg);
  }