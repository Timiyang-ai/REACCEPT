public double dirtyPriceFromCurvesWithZSpread(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      ReferenceData refData,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    LocalDate settlementDate = bond.getSettlementDateOffset().adjust(provider.getValuationDate(), refData);
    return dirtyPriceFromCurvesWithZSpread(bond, provider, zSpread, compoundedRateType, periodsPerYear, settlementDate);
  }