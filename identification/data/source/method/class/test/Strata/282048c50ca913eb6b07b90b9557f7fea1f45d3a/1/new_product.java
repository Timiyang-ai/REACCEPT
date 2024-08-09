public double presentValueSensitivityStrike(
      ResolvedCmsLeg cmsLeg,
      RatesProvider ratesProvider,
      SabrSwaptionVolatilities swaptionVolatilities) {

    validate(ratesProvider, swaptionVolatilities);
    return cmsLeg.getCmsPeriods().stream()
        .map(cmsPeriod -> cmsPeriodPricer.presentValueSensitivityStrike(cmsPeriod, ratesProvider, swaptionVolatilities))
        .collect(Collectors.summingDouble(Double::doubleValue));
  }