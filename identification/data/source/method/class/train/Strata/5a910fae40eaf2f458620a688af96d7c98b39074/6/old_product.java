@Override
  public ResolvedSwaptionTrade resolve(ReferenceData refData) {
    return ResolvedSwaptionTrade.builder()
        .info(info)
        .product(product.resolve(refData))
        .premium(premium)
        .build();
  }