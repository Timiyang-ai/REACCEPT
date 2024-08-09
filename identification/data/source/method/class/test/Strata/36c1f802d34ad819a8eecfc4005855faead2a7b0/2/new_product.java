public CurrencyAmount presentValueWithZSpread(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    return presentValueWithZSpread(bond, provider, zSpread, compoundedRateType, periodsPerYear, provider.getValuationDate());
  }