CurrencyAmount presentValueWithZSpread(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors issuerDf = issuerCurveDf(bond, provider);
    CurrencyAmount pvNominal = nominalPricer.presentValueWithSpread(
        bond.getNominalPayment(), issuerDf.getDiscountFactors(), zSpread, compoundedRateType, periodsPerYear);
    CurrencyAmount pvCoupon = presentValueCouponFromZSpread(
        bond, issuerDf, zSpread, compoundedRateType, periodsPerYear, referenceDate);
    return pvNominal.plus(pvCoupon);
  }