public PointSensitivityBuilder dirtyPriceSensitivity(
      ResolvedFixedCouponBond bond,
      StandardId securityId,
      LegalEntityDiscountingProvider provider) {

    LocalDate settlementDate = bond.getSettlementDateOffset().adjust(provider.getValuationDate());
    return dirtyPriceSensitivity(bond, securityId, provider, settlementDate);
  }