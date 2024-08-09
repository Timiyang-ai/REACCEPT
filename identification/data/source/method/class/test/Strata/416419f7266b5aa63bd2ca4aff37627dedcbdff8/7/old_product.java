public double dirtyPriceFromCurves(
      Security<FixedCouponBond> security,
      LegalEntityDiscountingProvider provider,
      LocalDate settlementDate) {

    FixedCouponBond product = security.getProduct();
    CurrencyAmount pv = presentValue(product, provider, settlementDate);
    StandardId securityId = security.getStandardId();
    StandardId legalEntityId = product.getLegalEntityId();
    double df = provider.repoCurveDiscountFactors(
        securityId, legalEntityId, product.getCurrency()).discountFactor(settlementDate);
    double notional = product.getNotional();
    return pv.getAmount() / df / notional;
  }