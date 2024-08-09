public CurrencyParameterSensitivity bucketedCs01(
      ResolvedCdsIndexTrade trade,
      List<ResolvedCdsIndexTrade> bucketCdsIndex,
      CreditRatesProvider ratesProvider,
      ReferenceData refData) {

    ResolvedCdsTrade cdsTrade = trade.toSingleNameCds();
    List<ResolvedCdsTrade> bucketCds = bucketCdsIndex.stream()
        .map(ResolvedCdsIndexTrade::toSingleNameCds)
        .collect(Collectors.toList());
    CurrencyParameterSensitivity bucketedCs01 = bucketedCs01(cdsTrade, bucketCds, ratesProvider, refData);
    double indexFactor = getIndexFactor(cdsTrade.getProduct(), ratesProvider);
    return bucketedCs01.multipliedBy(indexFactor);
  }