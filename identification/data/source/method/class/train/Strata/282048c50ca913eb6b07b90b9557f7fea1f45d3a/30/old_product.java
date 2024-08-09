public SwaptionSabrSensitivities presentValueSensitivitySabrParameter(
      ExpandedCmsLeg cmsLeg,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    validate(ratesProvider, swaptionVolatilities);
    List<SwaptionSabrSensitivity> sensitivities = cmsLeg
        .getCmsPeriods()
        .stream()
        .map(cmsPeriod -> cmsPeriodPricer.presentValueSensitivitySabrParameter(cmsPeriod, ratesProvider, swaptionVolatilities))
        .collect(Collectors.toList());
    return SwaptionSabrSensitivities.of(sensitivities).normalize();
  }