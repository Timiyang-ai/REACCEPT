PointSensitivityBuilder presentValueSensitivityWithZSpread(
      FixedCouponBond product,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear,
      LocalDate referenceDate) {

    ExpandedFixedCouponBond expanded = product.expand();
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        product.getLegalEntityId(), product.getCurrency());
    PointSensitivityBuilder pvNominal = presentValueSensitivityNominalFromZSpread(
        expanded, discountFactors, zSpread, compoundedRateType, periodsPerYear);
    boolean isExCoupon = product.getExCouponPeriod().getDays() != 0;
    PointSensitivityBuilder pvCoupon = presentValueSensitivityCouponFromZSpread(
        expanded, discountFactors, zSpread, compoundedRateType, periodsPerYear, referenceDate, isExCoupon);
    return pvNominal.combinedWith(pvCoupon);
  }