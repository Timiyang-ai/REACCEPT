public CurrencyAmount presentValueWithZSpread(
      FixedCouponBond product,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      boolean periodic,
      int periodsPerYear) {

    return presentValueWithZSpread(product, provider, zSpread, periodic, periodsPerYear, provider.getValuationDate());
  }