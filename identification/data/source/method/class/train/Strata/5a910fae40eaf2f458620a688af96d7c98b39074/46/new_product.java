@Override
  public ResolvedFxSwapTrade resolve(ReferenceData refData) {
    return ResolvedFxSwapTrade.of(info, product.resolve(refData));
  }