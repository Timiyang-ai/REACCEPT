PointSensitivityBuilder presentValueSensitivityWithZSpread(
      FixedCouponBond product,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      boolean periodic,
      int periodsPerYear,
      LocalDate referenceDate) {

    ExpandedFixedCouponBond expanded = product.expand();
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        product.getLegalEntityId(), product.getCurrency());
    PointSensitivityBuilder pvNominal = presentValueSensitivityNominalFromZSpread(
        expanded, discountFactors, zSpread, periodic, periodsPerYear);
    boolean isExCoupon = product.getExCouponPeriod().getDays() != 0;
    PointSensitivityBuilder pvCoupon = presentValueSensitivityCouponFromZSpread(
        expanded, discountFactors, zSpread, periodic, periodsPerYear, referenceDate, isExCoupon);
    return pvNominal.combinedWith(pvCoupon);
  }