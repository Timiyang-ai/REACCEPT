public double dirtyPriceFromCurvesWithZSpread(
      ResolvedFixedCouponBond bond,
      StandardId securityId,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    LocalDate settlementDate = bond.getSettlementDateOffset().adjust(provider.getValuationDate());
    return dirtyPriceFromCurvesWithZSpread(bond, securityId, provider, zSpread, compoundedRateType, periodsPerYear, settlementDate);
  }