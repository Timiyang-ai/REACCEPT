public PointSensitivityBuilder presentValueSensitivityWithZSpread(
      FixedCouponBond product,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      boolean periodic,
      int periodsPerYear) {

    return presentValueSensitivityWithZSpread(
        product, provider, zSpread, periodic, periodsPerYear, provider.getValuationDate());
  }