public PointSensitivityBuilder priceSensitivity(
      ResolvedCdsIndex cdsIndex,
      CreditRatesProvider ratesProvider,
      LocalDate referenceDate,
      ReferenceData refData) {

    ResolvedCds cds = cdsIndex.toSingleNameCds();
    return underlyingPricer.priceSensitivity(cds, ratesProvider, referenceDate, refData);
  }