public PointSensitivityBuilder presentValueSensitivity(
      ResolvedCmsLeg cmsLeg,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    validate(ratesProvider, swaptionVolatilities);
    return cmsLeg.getCmsPeriods().stream()
        .map(cmsPeriod -> cmsPeriodPricer.presentValueSensitivity(cmsPeriod, ratesProvider, swaptionVolatilities))
        .reduce((p1, p2) -> p1.combinedWith(p2))
        .get();
  }