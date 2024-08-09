@Override
  public ResolvedBondFutureOptionTrade resolve(ReferenceData refData) {
    return ResolvedBondFutureOptionTrade.builder()
        .tradeInfo(tradeInfo)
        .product(getProduct().resolve(refData))
        .securityStandardId(getSecurity().getStandardId())
        .quantity(quantity)
        .price(price)
        .build();
  }