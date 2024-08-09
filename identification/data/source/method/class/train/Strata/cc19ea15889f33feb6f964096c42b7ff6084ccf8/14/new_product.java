PointSensitivityBuilder dirtyPriceSensitivityWithZspread(
      ResolvedFixedCouponBond bond,
      StandardId securityId,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear,
      LocalDate referenceDate) {

    StandardId legalEntityId = bond.getLegalEntityId();
    RepoCurveDiscountFactors discountFactors =
        provider.repoCurveDiscountFactors(securityId, legalEntityId, bond.getCurrency());
    double df = discountFactors.discountFactor(referenceDate);
    CurrencyAmount pv = presentValueWithZSpread(bond, provider, zSpread, compoundedRateType, periodsPerYear);
    double notional = bond.getNotional();
    PointSensitivityBuilder pvSensi = presentValueSensitivityWithZSpread(
        bond, provider, zSpread, compoundedRateType, periodsPerYear).multipliedBy(1d / df / notional);
    RepoCurveZeroRateSensitivity dfSensi = discountFactors.zeroRatePointSensitivity(referenceDate)
        .multipliedBy(-pv.getAmount() / df / df / notional);
    return pvSensi.combinedWith(dfSensi);
  }