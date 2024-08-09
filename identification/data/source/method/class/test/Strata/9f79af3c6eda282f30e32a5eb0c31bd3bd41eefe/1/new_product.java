@Override
  public ResolvedIborCapFloorTrade resolve(ReferenceData refData) {
    return ResolvedIborCapFloorTrade.builder()
        .info(info)
        .product(product.resolve(refData))
        .premium(premium != null ? premium.resolve(refData) : null)
        .build();
  }