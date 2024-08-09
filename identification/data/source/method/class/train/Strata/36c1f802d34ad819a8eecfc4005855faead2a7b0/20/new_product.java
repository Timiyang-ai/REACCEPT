public CurrencyAmount presentValueWithZSpread(
      FixedCouponBond product,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    return presentValueWithZSpread(product, provider, zSpread, compoundedRateType, periodsPerYear, provider.getValuationDate());
  }