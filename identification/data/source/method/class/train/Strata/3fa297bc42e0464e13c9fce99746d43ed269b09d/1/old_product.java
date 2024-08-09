public PointSensitivityBuilder parSpreadSensitivity(
      ResolvedCdsIndex cdsIndex,
      CreditRatesProvider ratesProvider,
      LocalDate referenceDate,
      ReferenceData refData) {

    ResolvedCds cds = toSingleNameCds(cdsIndex);
    return underlyingPricer.parSpreadSensitivity(cds, ratesProvider, referenceDate, refData);
  }