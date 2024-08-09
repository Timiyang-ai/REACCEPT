public double dirtyPriceFromCurves(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      LocalDate settlementDate) {

    CurrencyAmount pv = presentValue(bond, provider, settlementDate);
    RepoCurveDiscountFactors repoDf = repoCurveDf(bond, provider);
    double df = repoDf.discountFactor(settlementDate);
    double notional = bond.getNotional();
    return pv.getAmount() / df / notional;
  }