@Override
  public ResolvedSwaptionTrade resolve(ReferenceData refData) {
    return ResolvedSwaptionTrade.builder()
        .tradeInfo(tradeInfo)
        .product(product.resolve(refData))
        .premium(premium)
        .build();
  }