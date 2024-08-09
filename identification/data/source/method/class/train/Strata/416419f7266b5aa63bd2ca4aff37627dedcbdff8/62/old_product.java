PointSensitivityBuilder presentValueSensitivity(
      FixedCouponBond product,
      LegalEntityDiscountingProvider provider,
      LocalDate referenceDate) {

    ExpandedFixedCouponBond expanded = product.expand();
    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        product.getLegalEntityId(), product.getCurrency());
    PointSensitivityBuilder pvNominal = presentValueSensitivityNominal(expanded, discountFactors);
    PointSensitivityBuilder pvCoupon = presentValueSensitivityCoupon(
        expanded, discountFactors, referenceDate, product.getExCouponPeriod().getDays() != 0);
    return pvNominal.combinedWith(pvCoupon);
  }