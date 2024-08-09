@Override
  public ResolvedFxNdfTrade resolve(ReferenceData refData) {
    return ResolvedFxNdfTrade.of(info, product.resolve(refData));
  }