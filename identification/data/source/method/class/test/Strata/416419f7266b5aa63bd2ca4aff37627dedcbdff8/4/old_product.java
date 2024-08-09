public CurrencyAmount presentValue(FixedCouponBond product, LegalEntityDiscountingProvider provider) {
    return presentValue(product, provider, provider.getValuationDate());
  }