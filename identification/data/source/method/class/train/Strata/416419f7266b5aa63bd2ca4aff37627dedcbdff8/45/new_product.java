CurrencyAmount presentValue(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      LocalDate referenceDate) {

    IssuerCurveDiscountFactors issuerDf = issuerCurveDf(bond, provider);
    CurrencyAmount pvNominal = nominalPricer.presentValue(bond.getNominalPayment(), issuerDf.getDiscountFactors());
    CurrencyAmount pvCoupon = presentValueCoupon(bond, issuerDf, referenceDate);
    return pvNominal.plus(pvCoupon);
  }