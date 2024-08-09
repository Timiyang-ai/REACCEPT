@Override
  public ResolvedFixedCouponBondTrade resolve(ReferenceData refData) {
    ResolvedFixedCouponBond resolved = getProduct().resolve(refData);
    TradeInfo completedInfo = calculateSettlementDate(refData);
    return new ResolvedFixedCouponBondTrade(completedInfo, resolved, quantity, price);
  }