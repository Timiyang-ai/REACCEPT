@Override
  public ResolvedIborFixingDepositTrade resolve(ReferenceData refData) {
    return ResolvedIborFixingDepositTrade.builder()
        .tradeInfo(tradeInfo)
        .product(product.resolve(refData))
        .build();
  }