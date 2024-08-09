double dirtyNominalPriceFromCurves(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      LocalDate settlementDate) {

    CurrencyAmount pv = presentValue(bond, ratesProvider, issuerDiscountFactorsProvider, settlementDate);
    StandardId legalEntityId = bond.getLegalEntityId();
    double df = issuerDiscountFactorsProvider.repoCurveDiscountFactors(
        bond.getSecurityId(), legalEntityId, bond.getCurrency()).discountFactor(settlementDate);
    double notional = bond.getNotional();
    return pv.getAmount() / (df * notional);
  }