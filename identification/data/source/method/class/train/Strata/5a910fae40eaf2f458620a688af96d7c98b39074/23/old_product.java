@Override
  public ResolvedSwapTrade resolve(ReferenceData refData) {
    return ResolvedSwapTrade.builder()
        .info(info)
        .product(product.resolve(refData))
        .build();
  }