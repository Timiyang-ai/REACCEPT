@Override
  public ResolvedIborCapFloorTrade resolve(ReferenceData refData) {
    return ResolvedIborCapFloorTrade.builder()
        .info(info)
        .product(product.resolve(refData))
        .premium(premium.resolve(refData))
        .build();
  }