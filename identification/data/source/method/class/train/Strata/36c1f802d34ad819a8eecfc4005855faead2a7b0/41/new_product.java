PointSensitivityBuilder presentValueSensitivityWithZSpread(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        bond.getLegalEntityId(), bond.getCurrency());
    PointSensitivityBuilder pvNominal = presentValueSensitivityNominalFromZSpread(
        bond, discountFactors, zSpread, compoundedRateType, periodsPerYear);
    boolean isExCoupon = bond.getExCouponPeriod().getDays() != 0;
    PointSensitivityBuilder pvCoupon = presentValueSensitivityCouponFromZSpread(
        bond, discountFactors, zSpread, compoundedRateType, periodsPerYear, referenceDate, isExCoupon);
    return pvNominal.combinedWith(pvCoupon);
  }