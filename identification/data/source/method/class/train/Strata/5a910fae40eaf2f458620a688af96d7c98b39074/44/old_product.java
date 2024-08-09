@Override
  public ResolvedFixedCouponBondTrade resolve(ReferenceData refData) {
    return ResolvedFixedCouponBondTrade.builder()
        .tradeInfo(tradeInfo)
        .product(getProduct().resolve(refData))
        .securityStandardId(getSecurity().getStandardId())
        .quantity(quantity)
        .price(price)
        .build();
  }