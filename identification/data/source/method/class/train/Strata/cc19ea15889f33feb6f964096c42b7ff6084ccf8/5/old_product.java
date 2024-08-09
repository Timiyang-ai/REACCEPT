public PointSensitivityBuilder dirtyPriceSensitivityWithZspread(
      ResolvedFixedCouponBond bond,
      StandardId securityId,
      LegalEntityDiscountingProvider provider,
      ReferenceData refData,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    LocalDate settlementDate = bond.getSettlementDateOffset().adjust(provider.getValuationDate(), refData);
    return dirtyPriceSensitivityWithZspread(bond, securityId, provider, zSpread, compoundedRateType, periodsPerYear, settlementDate);
  }