public CurrencyAmount presentValue(ResolvedFixedCouponBond bond, LegalEntityDiscountingProvider provider) {
    return presentValue(bond, provider, provider.getValuationDate());
  }