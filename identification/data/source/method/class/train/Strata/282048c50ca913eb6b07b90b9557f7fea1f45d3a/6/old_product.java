public CurrencyAmount presentValue(
      ExpandedCmsLeg cmsLeg,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    validate(ratesProvider, swaptionVolatilities);
    return cmsLeg.getCmsPeriods()
        .stream()
        .map(cmsPeriod -> cmsPeriodPricer.presentValue(cmsPeriod, ratesProvider, swaptionVolatilities))
        .reduce((c1, c2) -> c1.plus(c2))
        .get();
  }