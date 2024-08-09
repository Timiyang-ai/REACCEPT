public double dirtyPriceFromCurves(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      LocalDate settlementDate) {

    CurrencyAmount pv = presentValue(bond, provider, settlementDate);
    StandardId legalEntityId = bond.getLegalEntityId();
    double df = provider.repoCurveDiscountFactors(
        bond.getSecurityId(), legalEntityId, bond.getCurrency()).discountFactor(settlementDate);
    double notional = bond.getNotional();
    return pv.getAmount() / df / notional;
  }