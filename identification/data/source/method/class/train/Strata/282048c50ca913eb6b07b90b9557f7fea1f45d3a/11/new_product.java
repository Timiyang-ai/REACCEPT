public PointSensitivityBuilder presentValueSensitivityModelParamsSabr(
      ResolvedCmsLeg cmsLeg,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    validate(ratesProvider, swaptionVolatilities);
    return cmsLeg.getCmsPeriods().stream()
        .map(cmsPeriod -> cmsPeriodPricer.presentValueSensitivityModelParamsSabr(cmsPeriod, ratesProvider, swaptionVolatilities))
        .reduce(PointSensitivityBuilder.none(), PointSensitivityBuilder::combinedWith)
        .normalize();
  }