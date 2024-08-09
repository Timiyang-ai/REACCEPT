@Override
  public ResolvedTermDepositTrade resolve(ReferenceData refData) {
    return ResolvedTermDepositTrade.builder()
        .info(info)
        .product(product.resolve(refData))
        .build();
  }