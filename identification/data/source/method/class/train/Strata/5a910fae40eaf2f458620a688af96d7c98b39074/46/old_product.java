@Override
  public ResolvedFxSwapTrade resolve(ReferenceData refData) {
    return ResolvedFxSwapTrade.of(tradeInfo, product.resolve(refData));
  }