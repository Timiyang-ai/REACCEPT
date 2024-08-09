@Override
  public ResolvedBondFuture resolve(ReferenceData refData) {
    // TODO: remove list of pairs
    List<Pair<ResolvedFixedCouponBond, StandardId>> basket = deliveryBasket.stream()
        .map(link -> Pair.of(link.resolvedTarget().getProduct().resolve(refData), link.resolvedTarget().getStandardId()))
        .collect(toImmutableList());
    FixedCouponBond product0 = deliveryBasket.get(0).resolvedTarget().getProduct();
    DaysAdjustment settleOffset = product0.getSettlementDateOffset();
    return ResolvedBondFuture.builder()
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