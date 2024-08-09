public double dirtyNominalPriceFromCurvesWithZSpread(
      ResolvedCapitalIndexedBond bond,
      StandardId securityId,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = bond.getSettlementDateOffset().adjust(ratesProvider.getValuationDate(), REF_DATA);
    return dirtyNominalPriceFromCurvesWithZSpread(bond, securityId, ratesProvider, issuerDiscountFactorsProvider,
        settlementDate, zSpread, compoundedRateType, periodsPerYear);
  }