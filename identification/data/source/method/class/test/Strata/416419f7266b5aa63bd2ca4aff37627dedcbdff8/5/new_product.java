public CurrencyAmount presentValue(ResolvedFixedCouponBondTrade trade, LegalEntityDiscountingProvider provider) {
    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    CurrencyAmount pvProduct = productPricer.presentValue(trade.getProduct(), provider, settlementDate);
    return presentValueFromProductPresentValue(trade, provider, pvProduct);
  }