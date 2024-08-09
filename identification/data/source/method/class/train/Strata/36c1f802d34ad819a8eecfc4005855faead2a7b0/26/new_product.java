public double dirtyPriceFromCurvesWithZSpread(
      ResolvedFixedCouponBond bond,
      StandardId securityId,
      LegalEntityDiscountingProvider provider,
      ReferenceData refData,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    LocalDate settlementDate = bond.getSettlementDateOffset().adjust(provider.getValuationDate(), refData);
    return dirtyPriceFromCurvesWithZSpread(bond, securityId, provider, zSpread, compoundedRateType, periodsPerYear, settlementDate);
  }