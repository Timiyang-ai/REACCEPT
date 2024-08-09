public double dirtyPriceFromCurvesWithZSpread(
      Security<FixedCouponBond> security,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      boolean periodic,
      int periodsPerYear,
      LocalDate settlementDate) {

    FixedCouponBond product = security.getProduct();
    CurrencyAmount pv = presentValueWithZSpread(product, provider, zSpread, periodic, periodsPerYear, settlementDate);
    StandardId securityId = security.getStandardId();
    StandardId legalEntityId = product.getLegalEntityId();
    double df = provider.repoCurveDiscountFactors(
        securityId, legalEntityId, product.getCurrency()).discountFactor(settlementDate);
    double notional = product.getNotional();
    return pv.getAmount() / df / notional;
  }