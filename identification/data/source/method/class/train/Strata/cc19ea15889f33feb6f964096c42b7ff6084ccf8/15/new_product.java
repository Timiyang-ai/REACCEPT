PointSensitivityBuilder dirtyPriceSensitivity(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      LocalDate referenceDate) {

    RepoCurveDiscountFactors repoDf = repoCurveDf(bond, provider);
    double df = repoDf.discountFactor(referenceDate);
    CurrencyAmount pv = presentValue(bond, provider);
    double notional = bond.getNotional();
    PointSensitivityBuilder pvSensi = presentValueSensitivity(bond, provider).multipliedBy(1d / df / notional);
    RepoCurveZeroRateSensitivity dfSensi = repoDf.zeroRatePointSensitivity(referenceDate)
        .multipliedBy(-pv.getAmount() / df / df / notional);
    return pvSensi.combinedWith(dfSensi);
  }