public PointSensitivityBuilder presentValueSensitivity(
      CmsProduct cms,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    ExpandedCms expanded = cms.expand();
    PointSensitivityBuilder pvSensiCmsLeg =
        cmsLegPricer.presentValueSensitivity(expanded.getCmsLeg(), ratesProvider, swaptionVolatilities);
    if (!expanded.getPayLeg().isPresent()) {
      return pvSensiCmsLeg;
    }
    PointSensitivityBuilder pvSensiPayLeg = payLegPricer.presentValueSensitivity(expanded.getPayLeg().get(), ratesProvider);
    return pvSensiCmsLeg.combinedWith(pvSensiPayLeg);
  }