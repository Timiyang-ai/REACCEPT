@Override
  public ResolvedSwapTrade resolve(ReferenceData refData) {
    return new ResolvedSwapTrade(info, product.resolve(refData));
  }