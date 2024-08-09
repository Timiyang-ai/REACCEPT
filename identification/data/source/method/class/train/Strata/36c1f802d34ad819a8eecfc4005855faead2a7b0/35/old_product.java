public CurrencyAmount presentValueWithZSpread(
      ResolvedFixedCouponBondTrade trade,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    LocalDate settlementDate = trade.getSettlementDate();
    CurrencyAmount pvProduct = productPricer.presentValueWithZSpread(
        trade.getProduct(), provider, zSpread, compoundedRateType, periodsPerYear, settlementDate);
    return presentValueFromProductPresentValue(trade, provider, pvProduct);
  }