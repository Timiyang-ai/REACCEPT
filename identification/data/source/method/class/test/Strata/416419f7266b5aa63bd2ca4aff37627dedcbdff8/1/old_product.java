PointSensitivityBuilder presentValueSensitivity(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors discountFactors = provider.issuerCurveDiscountFactors(
        bond.getLegalEntityId(), bond.getCurrency());
    PointSensitivityBuilder pvNominal = presentValueSensitivityNominal(bond, discountFactors);
    PointSensitivityBuilder pvCoupon = presentValueSensitivityCoupon(
        bond, discountFactors, referenceDate, bond.getExCouponPeriod().getDays() != 0);
    return pvNominal.combinedWith(pvCoupon);
  }