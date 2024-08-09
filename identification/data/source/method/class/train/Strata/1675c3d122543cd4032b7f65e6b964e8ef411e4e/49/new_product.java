public double dirtyNominalPriceFromCurvesWithZSpread(
      ResolvedCapitalIndexedBond bond,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      ReferenceData refData,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, discountingProvider);
    LocalDate settlementDate = bond.calculateSettlementDateFromValuation(ratesProvider.getValuationDate(), refData);
    return dirtyNominalPriceFromCurvesWithZSpread(bond, ratesProvider, discountingProvider,
        settlementDate, zSpread, compoundedRateType, periodsPerYear);
  }