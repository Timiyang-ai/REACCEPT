@Override
  public ResolvedFixedCouponBondTrade resolve(ReferenceData refData) {
    ResolvedFixedCouponBond resolved = getProduct().resolve(refData);
    return new ResolvedFixedCouponBondTrade(tradeInfo, resolved, quantity, price);
  }