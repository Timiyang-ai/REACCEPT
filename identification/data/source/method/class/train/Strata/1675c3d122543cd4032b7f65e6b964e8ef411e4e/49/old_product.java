public double dirtyNominalPriceFromCurvesWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    return dirtyNominalPriceFromCurvesWithZSpread(bond, ratesProvider, issuerDiscountFactorsProvider,
        settlementDate, zSpread, compoundedRateType, periodsPerYear);
  }