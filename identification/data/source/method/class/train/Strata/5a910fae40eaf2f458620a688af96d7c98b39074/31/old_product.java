@Override
  public ResolvedIborFutureTrade resolve(ReferenceData refData) {
    return ResolvedIborFutureTrade.builder()
        .tradeInfo(tradeInfo)
        .product(getProduct().resolve(refData))
        .securityStandardId(getSecurity().getStandardId())
        .quantity(quantity)
        .price(price)
        .build();
  }