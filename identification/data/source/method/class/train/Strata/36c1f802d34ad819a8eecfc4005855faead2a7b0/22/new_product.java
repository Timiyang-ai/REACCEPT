public double dirtyPriceFromCurvesWithZSpread(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear,
      LocalDate settlementDate) {

    CurrencyAmount pv = presentValueWithZSpread(bond, provider, zSpread, compoundedRateType, periodsPerYear, settlementDate);
    RepoCurveDiscountFactors repoDf = repoCurveDf(bond, provider);
    double df = repoDf.discountFactor(settlementDate);
    double notional = bond.getNotional();
    return pv.getAmount() / df / notional;
  }