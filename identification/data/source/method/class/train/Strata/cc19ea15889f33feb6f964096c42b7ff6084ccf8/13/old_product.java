PointSensitivityBuilder dirtyPriceSensitivity(
      Security<FixedCouponBond> security,
      LegalEntityDiscountingProvider provider,
      LocalDate referenceDate) {

    FixedCouponBond product = security.getProduct();
    StandardId securityId = security.getStandardId();
    StandardId legalEntityId = product.getLegalEntityId();
    RepoCurveDiscountFactors discountFactors =
        provider.repoCurveDiscountFactors(securityId, legalEntityId, product.getCurrency());
    double df = discountFactors.discountFactor(referenceDate);
    CurrencyAmount pv = presentValue(product, provider);
    double notional = product.getNotional();
    PointSensitivityBuilder pvSensi = presentValueSensitivity(product, provider).multipliedBy(1d / df / notional);
    RepoCurveZeroRateSensitivity dfSensi = discountFactors.zeroRatePointSensitivity(referenceDate)
        .multipliedBy(-pv.getAmount() / df / df / notional);
    return pvSensi.combinedWith(dfSensi);
  }