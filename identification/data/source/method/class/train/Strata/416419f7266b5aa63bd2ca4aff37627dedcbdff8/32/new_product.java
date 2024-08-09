public CurrencyAmount presentValue(ResolvedFixedCouponBondTrade trade, LegalEntityDiscountingProvider provider) {
    LocalDate settlementDate = settlementDate(trade, provider.getValuationDate());
    CurrencyAmount pvProduct = productPricer.presentValue(trade.getProduct(), provider, settlementDate);
    return presentValueFromProductPresentValue(trade, provider, pvProduct);
  }