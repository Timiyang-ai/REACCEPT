PointSensitivityBuilder presentValueSensitivity(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors issuerDf = issuerCurveDf(bond, provider);
    PointSensitivityBuilder pvNominal = presentValueSensitivityNominal(bond, issuerDf);
    PointSensitivityBuilder pvCoupon = presentValueSensitivityCoupon(bond, issuerDf, referenceDate);
    return pvNominal.combinedWith(pvCoupon);
  }