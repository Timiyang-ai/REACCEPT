@Override
  public ResolvedFraTrade resolve(ReferenceData refData) {
    return ResolvedFraTrade.builder()
        .info(info)
        .product(product.resolve(refData))
        .build();
  }