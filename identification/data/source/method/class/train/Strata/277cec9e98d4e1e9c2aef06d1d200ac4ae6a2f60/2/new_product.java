@Override
  public ResolvedBondFuture resolve(ReferenceData refData) {
    List<ResolvedFixedCouponBond> basket = deliveryBasket.stream()
        .map(bond -> bond.resolve(refData))
        .collect(toImmutableList());
    DaysAdjustment settleOffset = deliveryBasket.get(0).getSettlementDateOffset();
    return ResolvedBondFuture.builder()
        .securityId(securityId)
        .deliveryBasket(basket)
        .conversionFactors(conversionFactors)
        .lastTradeDate(lastTradeDate)
        .firstNoticeDate(firstNoticeDate)
        .lastNoticeDate(lastNoticeDate)
        .firstDeliveryDate(firstDeliveryDate != null ? firstDeliveryDate : settleOffset.adjust(firstNoticeDate, refData))
        .lastDeliveryDate(lastDeliveryDate != null ? lastDeliveryDate : settleOffset.adjust(lastNoticeDate, refData))
        .rounding(rounding)
        .build();
  }