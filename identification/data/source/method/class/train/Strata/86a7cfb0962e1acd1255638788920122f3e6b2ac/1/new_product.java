public CurrencyParameterSensitivity bucketedCs01(
      ResolvedCdsTrade trade,
      List<ResolvedCdsTrade> bucketCds,
      CreditRatesProvider ratesProvider,
      ReferenceData refData) {

    List<ResolvedTradeParameterMetadata> metadata = bucketCds.stream()
        .map(t -> ResolvedTradeParameterMetadata.of(t, t.getProduct().getProtectionEndDate().toString()))
        .collect(Guavate.toImmutableList());
    return bucketedCs01(trade, bucketCds, metadata, ratesProvider, refData);
  }