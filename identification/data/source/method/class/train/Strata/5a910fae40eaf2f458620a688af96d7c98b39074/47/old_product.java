@Override
  public ResolvedSwapTrade resolve(ReferenceData refData) {
    return ResolvedSwapTrade.builder()
        .tradeInfo(tradeInfo)
        .product(product.resolve(refData))
        .build();
  }