public MultiCurrencyAmount currencyExposure(ResolvedFixedCouponBondTrade trade, LegalEntityDiscountingProvider provider) {

    return MultiCurrencyAmount.of(presentValue(trade, provider));
  }