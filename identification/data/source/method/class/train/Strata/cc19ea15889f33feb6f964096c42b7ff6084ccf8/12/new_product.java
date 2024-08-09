public PointSensitivityBuilder dirtyPriceSensitivity(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      ReferenceData refData) {

    LocalDate settlementDate = bond.getSettlementDateOffset().adjust(provider.getValuationDate(), refData);
    return dirtyPriceSensitivity(bond, provider, settlementDate);
  }