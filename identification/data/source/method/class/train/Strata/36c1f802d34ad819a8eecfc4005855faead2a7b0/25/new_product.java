public PointSensitivityBuilder presentValueSensitivityWithZSpread(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    return presentValueSensitivityWithZSpread(
        bond, provider, zSpread, compoundedRateType, periodsPerYear, provider.getValuationDate());
  }