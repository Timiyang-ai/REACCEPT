PointSensitivityBuilder presentValueSensitivityWithZSpread(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors issuerDf = issuerCurveDf(bond, provider);
    PointSensitivityBuilder pvNominal = presentValueSensitivityNominalFromZSpread(
        bond, issuerDf, zSpread, compoundedRateType, periodsPerYear);
    PointSensitivityBuilder pvCoupon = presentValueSensitivityCouponFromZSpread(
        bond, issuerDf, zSpread, compoundedRateType, periodsPerYear, referenceDate);
    return pvNominal.combinedWith(pvCoupon);
  }