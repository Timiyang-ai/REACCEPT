public CurrencyParameterSensitivity bucketedCs01(
      ResolvedCdsTrade trade,
      List<ResolvedCdsTrade> bucketCds,
      CreditRatesProvider ratesProvider,
      ReferenceData refData) {

    DoubleArray sensiValue = computedBucketedCs01(trade, bucketCds, ratesProvider, refData);
    return CurrencyParameterSensitivity.of(CurveName.of("impliedSpreads"), trade.getProduct().getCurrency(), sensiValue);
  }