@Override
  public ResolvedFxSingleTrade resolve(ReferenceData refData) {
    return ResolvedFxSingleTrade.of(tradeInfo, product.resolve(refData));
  }