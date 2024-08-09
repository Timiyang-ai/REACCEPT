@Override
  public ResolvedIborFixingDepositTrade resolve(ReferenceData refData) {
    return ResolvedIborFixingDepositTrade.builder()
        .info(info)
        .product(product.resolve(refData))
        .build();
  }