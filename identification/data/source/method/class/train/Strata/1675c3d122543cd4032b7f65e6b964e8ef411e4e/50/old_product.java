double dirtyNominalPriceFromCurves(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate settlementDate) {

    CurrencyAmount pv = presentValue(bond, ratesProvider, discountingProvider, settlementDate);
    StandardId legalEntityId = bond.getLegalEntityId();
    double df = discountingProvider.repoCurveDiscountFactors(
        bond.getSecurityId(), legalEntityId, bond.getCurrency()).discountFactor(settlementDate);
    double notional = bond.getNotional();
    return pv.getAmount() / (df * notional);
  }