@Override
  public ResolvedTermDepositTrade resolve(ReferenceData refData) {
    return ResolvedTermDepositTrade.builder()
        .tradeInfo(tradeInfo)
        .product(product.resolve(refData))
        .build();
  }