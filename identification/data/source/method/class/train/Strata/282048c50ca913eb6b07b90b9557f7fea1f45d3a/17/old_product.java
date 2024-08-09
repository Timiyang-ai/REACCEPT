public double presentValueSensitivityStrike(
      ExpandedCmsLeg cmsLeg,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    validate(ratesProvider, swaptionVolatilities);
    return cmsLeg
        .getCmsPeriods()
        .stream()
        .map(cmsPeriod -> cmsPeriodPricer.presentValueSensitivityStrike(cmsPeriod, ratesProvider, swaptionVolatilities))
        .collect(Collectors.summingDouble(Double::doubleValue));
  }