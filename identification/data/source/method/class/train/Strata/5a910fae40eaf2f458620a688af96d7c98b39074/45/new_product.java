@Override
  public ResolvedCmsTrade resolve(ReferenceData refData) {
    return ResolvedCmsTrade.builder()
        .info(info)
        .product(product.resolve(refData))
        .premium(premium != null ? premium.resolve(refData) : null)
        .build();
  }