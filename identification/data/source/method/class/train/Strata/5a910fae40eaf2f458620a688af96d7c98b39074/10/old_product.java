@Override
  public ResolvedBondFutureTrade resolve(ReferenceData refData) {
    return ResolvedBondFutureTrade.builder()
        .tradeInfo(tradeInfo)
        .product(getProduct().resolve(refData))
        .securityStandardId(getSecurity().getStandardId())
        .quantity(quantity)
        .price(price)
        .build();
  }