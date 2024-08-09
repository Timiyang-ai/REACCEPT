double dirtyNominalPriceFromCurves(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate settlementDate) {

    CurrencyAmount pv = presentValue(bond, ratesProvider, discountingProvider, settlementDate);
    RepoCurveDiscountFactors repoDf = repoCurveDf(bond, discountingProvider);
    double df = repoDf.discountFactor(settlementDate);
    double notional = bond.getNotional();
    return pv.getAmount() / (df * notional);
  }