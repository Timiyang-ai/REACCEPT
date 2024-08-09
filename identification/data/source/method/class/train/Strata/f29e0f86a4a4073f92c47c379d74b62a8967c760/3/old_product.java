public SplitCurrencyAmount<StandardId> jumpToDefault(
      ResolvedCds cds,
      CreditRatesProvider ratesProvider,
      LocalDate referenceDate,
      ReferenceData refData) {

    StandardId legalEntityId = cds.getLegalEntityId();
    Currency currency = cds.getCurrency();
    if (isExpired(cds, ratesProvider)) {
      return SplitCurrencyAmount.of(currency, ImmutableMap.of(legalEntityId, 0d));
    }
    LocalDate stepinDate = cds.getStepinDateOffset().adjust(ratesProvider.getValuationDate(), refData);
    LocalDate effectiveStartDate = cds.calculateEffectiveStartDate(stepinDate);
    double recoveryRate = recoveryRate(cds, ratesProvider);
    Pair<CreditDiscountFactors, LegalEntitySurvivalProbabilities> rates = reduceDiscountFactors(cds, ratesProvider);
    double protectionFull = protectionFull(cds, rates.getFirst(), rates.getSecond(), referenceDate, effectiveStartDate);
    double lgd = 1d - recoveryRate;
    double rpv01 = riskyAnnuity(
        cds, rates.getFirst(), rates.getSecond(), referenceDate, stepinDate, effectiveStartDate, PriceType.CLEAN);
    double jtd = lgd - (lgd * protectionFull - cds.getFixedRate() * rpv01);
    return SplitCurrencyAmount.of(currency, ImmutableMap.of(legalEntityId, cds.getBuySell().normalize(cds.getNotional()) * jtd));
  }