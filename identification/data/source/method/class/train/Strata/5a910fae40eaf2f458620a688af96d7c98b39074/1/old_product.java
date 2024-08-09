@Override
  public ResolvedFraTrade resolve(ReferenceData refData) {
    return ResolvedFraTrade.builder()
        .tradeInfo(tradeInfo)
        .product(product.resolve(refData))
        .build();
  }