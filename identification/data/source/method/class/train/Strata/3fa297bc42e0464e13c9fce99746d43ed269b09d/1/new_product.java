public PointSensitivityBuilder parSpreadSensitivity(
      ResolvedCdsIndex cdsIndex,
      CreditRatesProvider ratesProvider,
      LocalDate referenceDate,
      ReferenceData refData) {

    ResolvedCds cds = cdsIndex.toSingleNameCds();
    return underlyingPricer.parSpreadSensitivity(cds, ratesProvider, referenceDate, refData);
  }