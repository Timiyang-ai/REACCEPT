@Override
  public ResolvedBulletPaymentTrade resolve(ReferenceData refData) {
    return ResolvedBulletPaymentTrade.of(tradeInfo, product.resolve(refData));
  }