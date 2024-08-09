public CurrencyParameterSensitivity bucketedCs01(
      ResolvedCdsIndexTrade trade,
      List<ResolvedCdsIndexTrade> bucketCdsIndex,
      CreditRatesProvider ratesProvider,
      ReferenceData refData) {

    ResolvedCdsTrade cdsTrade = trade.toSingleNameCds();
    List<ResolvedCdsTrade> bucketCds = bucketCdsIndex.stream()
        .map(ResolvedCdsIndexTrade::toSingleNameCds)
        .collect(Collectors.toList());
    List<ResolvedTradeParameterMetadata> metadata = bucketCdsIndex.stream()
        .map(t -> ResolvedTradeParameterMetadata.of(t, t.getProduct().getProtectionEndDate().toString()))
        .collect(Guavate.toImmutableList());
    CurrencyParameterSensitivity bucketedCs01 = bucketedCs01(cdsTrade, bucketCds, metadata, ratesProvider, refData);
    double indexFactor = getIndexFactor(cdsTrade.getProduct(), ratesProvider);
    return bucketedCs01.multipliedBy(indexFactor);
  }