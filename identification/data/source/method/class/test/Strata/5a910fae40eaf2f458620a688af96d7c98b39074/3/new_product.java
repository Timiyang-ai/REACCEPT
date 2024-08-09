@Override
  public ResolvedFxSingleTrade resolve(ReferenceData refData) {
    return ResolvedFxSingleTrade.of(info, product.resolve(refData));
  }