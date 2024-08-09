double dirtyNominalPriceFromCurvesWithZSpread(
      ResolvedCapitalIndexedBond bond,
      StandardId securityId,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      LocalDate settlementDate,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    CurrencyAmount pv = presentValueWithZSpread(
        bond, ratesProvider, issuerDiscountFactorsProvider, settlementDate, zSpread, compoundedRateType,
        periodsPerYear);
    StandardId legalEntityId = bond.getLegalEntityId();
    double df = issuerDiscountFactorsProvider.repoCurveDiscountFactors(
        securityId, legalEntityId, bond.getCurrency()).discountFactor(settlementDate);
    double notional = bond.getNotional();
    return pv.getAmount() / (df * notional);
  }