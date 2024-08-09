public PointSensitivityBuilder priceSensitivity(
      ResolvedCdsIndex cdsIndex,
      CreditRatesProvider ratesProvider,
      LocalDate referenceDate,
      ReferenceData refData) {

    ResolvedCds cds = toSingleNameCds(cdsIndex);
    return underlyingPricer.priceSensitivity(cds, ratesProvider, referenceDate, refData);
  }