public SplitCurrencyAmount<StandardId> jumpToDefault(
      ResolvedCdsIndex cdsIndex,
      CreditRatesProvider ratesProvider,
      LocalDate referenceDate,
      ReferenceData refData) {

    StandardId indexId = cdsIndex.getCdsIndexId();
    Currency currency = cdsIndex.getCurrency();
    if (isExpired(cdsIndex, ratesProvider)) {
      return SplitCurrencyAmount.of(currency, ImmutableMap.of(indexId, 0d));
    }
    ResolvedCds cds = cdsIndex.toSingleNameCds();
    LocalDate stepinDate = cds.getStepinDateOffset().adjust(ratesProvider.getValuationDate(), refData);
    LocalDate effectiveStartDate = cds.calculateEffectiveStartDate(stepinDate);
    double recoveryRate = underlyingPricer.recoveryRate(cds, ratesProvider);
    Triple<CreditDiscountFactors, LegalEntitySurvivalProbabilities, Double> rates = reduceDiscountFactors(cds, ratesProvider);
    double protectionFull = underlyingPricer.protectionFull(
        cds, rates.getFirst(), rates.getSecond(), referenceDate, effectiveStartDate);
    double rpv01 = underlyingPricer.riskyAnnuity(
        cds, rates.getFirst(), rates.getSecond(), referenceDate, stepinDate, effectiveStartDate, PriceType.CLEAN);
    double lgd = 1d - recoveryRate;
    double numTotal = cdsIndex.getLegalEntityIds().size();
    double jtd = (lgd - (lgd * protectionFull - cds.getFixedRate() * rpv01)) / numTotal;
    return SplitCurrencyAmount.of(currency, ImmutableMap.of(indexId, cds.getBuySell().normalize(cds.getNotional()) * jtd));
  }