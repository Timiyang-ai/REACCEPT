double dirtyNominalPriceFromCurvesWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      LocalDate settlementDate,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    CurrencyAmount pv = presentValueWithZSpread(
        bond, ratesProvider, discountingProvider, settlementDate, zSpread, compoundedRateType,
        periodsPerYear);
    StandardId legalEntityId = bond.getLegalEntityId();
    double df = discountingProvider.repoCurveDiscountFactors(
        bond.getSecurityId(), legalEntityId, bond.getCurrency()).discountFactor(settlementDate);
    double notional = bond.getNotional();
    return pv.getAmount() / (df * notional);
  }