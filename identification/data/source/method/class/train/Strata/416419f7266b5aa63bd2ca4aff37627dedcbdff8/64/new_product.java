public double dirtyPriceFromCurves(
      ResolvedFixedCouponBond bond,
      StandardId securityId,
      LegalEntityDiscountingProvider provider) {

    LocalDate settlementDate = bond.getSettlementDateOffset().adjust(provider.getValuationDate());
    return dirtyPriceFromCurves(bond, securityId, provider, settlementDate);
  }