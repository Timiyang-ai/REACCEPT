@Override
  public ResolvedFxNdfTrade resolve(ReferenceData refData) {
    return ResolvedFxNdfTrade.of(tradeInfo, product.resolve(refData));
  }