public PointSensitivityBuilder priceSensitivity(
      ResolvedCds cds,
      CreditRatesProvider ratesProvider,
      LocalDate referenceDate,
      ReferenceData refData) {

    if (!cds.getProtectionEndDate().isAfter(ratesProvider.getValuationDate())) { //short cut already expired CDSs
      return PointSensitivityBuilder.none();
    }
    LocalDate stepinDate = cds.getStepinDateOffset().adjust(ratesProvider.getValuationDate(), refData);
    LocalDate effectiveStartDate = cds.calculateEffectiveStartDate(stepinDate);
    double recoveryRate = recoveryRate(cds, ratesProvider);
    Pair<CreditDiscountFactors, LegalEntitySurvivalProbabilities> rates = reduceDiscountFactors(cds, ratesProvider);

    PointSensitivityBuilder protectionLegSensi =
        protectionLegSensitivity(cds, rates.getFirst(), rates.getSecond(), referenceDate, effectiveStartDate, recoveryRate);
    PointSensitivityBuilder riskyAnnuitySensi =
        riskyAnnuitySensitivity(cds, rates.getFirst(), rates.getSecond(), referenceDate, stepinDate, effectiveStartDate)
            .multipliedBy(-cds.getFixedRate());

    return protectionLegSensi.combinedWith(riskyAnnuitySensi);
  }