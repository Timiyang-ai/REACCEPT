public CurrencyAmount currentCash(
      ResolvedCmsLeg cmsLeg,
      RatesProvider ratesProvider,
      SabrParametersSwaptionVolatilities swaptionVolatilities) {

    validate(ratesProvider, swaptionVolatilities);
    return cmsLeg.getCmsPeriods()
        .stream()
        .filter(x -> x.getPaymentDate().equals(ratesProvider.getValuationDate()))
        .map(x -> cmsPeriodPricer.presentValue(x, ratesProvider, swaptionVolatilities))
        .reduce((c1, c2) -> c1.plus(c2))
        .orElse(CurrencyAmount.zero(cmsLeg.getCurrency()));
  }