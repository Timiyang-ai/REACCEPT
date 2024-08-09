public PointSensitivityBuilder dirtyPriceSensitivity(
      Security<FixedCouponBond> security,
      LegalEntityDiscountingProvider provider) {

    FixedCouponBond product = security.getProduct();
    LocalDate settlementDate = product.getSettlementDateOffset().adjust(provider.getValuationDate());
    StandardId securityId = security.getStandardId();
    StandardId legalEntityId = product.getLegalEntityId();
    RepoCurveDiscountFactors discountFactors =
        provider.repoCurveDiscountFactors(securityId, legalEntityId, product.getCurrency());
    double df = discountFactors.discountFactor(settlementDate);
    CurrencyAmount pv = presentValue(product, provider);
    double notional = product.getNotional();
    PointSensitivityBuilder pvSensi = presentValueSensitivity(product, provider).multipliedBy(1d / df / notional);
    RepoCurveZeroRateSensitivity dfSensi = discountFactors.zeroRatePointSensitivity(settlementDate)
        .multipliedBy(-pv.getAmount() / df / df / notional);
    return pvSensi.combinedWith(dfSensi);
  }