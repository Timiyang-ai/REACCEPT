public double dirtyPriceFromCurves(Security<FixedCouponBond> security, LegalEntityDiscountingProvider provider) {
    FixedCouponBond product = security.getProduct();
    LocalDate settlementDate = product.getSettlementDateOffset().adjust(provider.getValuationDate());
    return dirtyPriceFromCurves(security, provider, settlementDate);
  }