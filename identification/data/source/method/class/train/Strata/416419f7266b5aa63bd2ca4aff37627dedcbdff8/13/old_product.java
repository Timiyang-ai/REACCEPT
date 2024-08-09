public MultiCurrencyAmount currencyExposure(FixedCouponBondTrade trade, LegalEntityDiscountingProvider provider) {

    return MultiCurrencyAmount.of(presentValue(trade, provider));
  }