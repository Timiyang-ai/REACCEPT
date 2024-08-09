public double dirtyPriceFromCurvesWithZSpread(
      Security<FixedCouponBond> security,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear,
      LocalDate settlementDate) {

    FixedCouponBond product = security.getProduct();
    CurrencyAmount pv = presentValueWithZSpread(product, provider, zSpread, compoundedRateType, periodsPerYear, settlementDate);
    StandardId securityId = security.getStandardId();
    StandardId legalEntityId = product.getLegalEntityId();
    double df = provider.repoCurveDiscountFactors(
        securityId, legalEntityId, product.getCurrency()).discountFactor(settlementDate);
    double notional = product.getNotional();
    return pv.getAmount() / df / notional;
  }