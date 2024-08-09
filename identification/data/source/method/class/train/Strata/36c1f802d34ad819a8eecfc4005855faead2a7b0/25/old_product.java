public PointSensitivityBuilder presentValueSensitivityWithZSpread(
      FixedCouponBond product,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    return presentValueSensitivityWithZSpread(
        product, provider, zSpread, compoundedRateType, periodsPerYear, provider.getValuationDate());
  }