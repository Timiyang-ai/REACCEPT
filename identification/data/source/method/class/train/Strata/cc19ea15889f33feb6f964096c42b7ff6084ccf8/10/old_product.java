public PointSensitivityBuilder dirtyPriceSensitivityWithZspread(
      Security<FixedCouponBond> security,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      boolean periodic,
      int periodsPerYear) {

    FixedCouponBond product = security.getProduct();
    LocalDate settlementDate = product.getSettlementDateOffset().adjust(provider.getValuationDate());
    StandardId securityId = security.getStandardId();
    StandardId legalEntityId = product.getLegalEntityId();
    RepoCurveDiscountFactors discountFactors =
        provider.repoCurveDiscountFactors(securityId, legalEntityId, product.getCurrency());
    double df = discountFactors.discountFactor(settlementDate);
    CurrencyAmount pv = presentValueWithZSpread(product, provider, zSpread, periodic, periodsPerYear);
    double notional = product.getNotional();
    PointSensitivityBuilder pvSensi = presentValueSensitivityWithZSpread(
        product, provider, zSpread, periodic, periodsPerYear).multipliedBy(1d / df / notional);
    RepoCurveZeroRateSensitivity dfSensi = discountFactors.zeroRatePointSensitivity(settlementDate)
        .multipliedBy(-pv.getAmount() / df / df / notional);
    return pvSensi.combinedWith(dfSensi);
  }