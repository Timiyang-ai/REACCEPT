@Override
  public ResolvedCmsTrade resolve(ReferenceData refData) {
    return ResolvedCmsTrade.builder()
        .tradeInfo(tradeInfo)
        .product(product.resolve(refData))
        .premium(premium)
        .build();
  }