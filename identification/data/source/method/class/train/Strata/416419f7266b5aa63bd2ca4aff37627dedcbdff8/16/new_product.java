public double dirtyPriceFromCurves(
      ResolvedFixedCouponBond bond,
      LegalEntityDiscountingProvider provider,
      ReferenceData refData) {

    LocalDate settlementDate = bond.getSettlementDateOffset().adjust(provider.getValuationDate(), refData);
    return dirtyPriceFromCurves(bond, provider, settlementDate);
  }