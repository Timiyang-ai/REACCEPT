@Override
  public ResolvedBulletPaymentTrade resolve(ReferenceData refData) {
    return ResolvedBulletPaymentTrade.of(info, product.resolve(refData));
  }