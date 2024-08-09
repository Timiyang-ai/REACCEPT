@Override
  public ResolvedIborFutureOptionTrade resolve(ReferenceData refData) {
    return ResolvedIborFutureOptionTrade.builder()
        .tradeInfo(tradeInfo)
        .product(getProduct().resolve(refData))
        .securityStandardId(getSecurity().getStandardId())
        .quantity(quantity)
        .price(price)
        .build();
  }