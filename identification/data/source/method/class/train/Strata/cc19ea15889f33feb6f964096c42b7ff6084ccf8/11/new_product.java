public PointSensitivityBuilder dirtyPriceSensitivity(
      Security<FixedCouponBond> security,
      LegalEntityDiscountingProvider provider) {

    FixedCouponBond product = security.getProduct();
    LocalDate settlementDate = product.getSettlementDateOffset().adjust(provider.getValuationDate());
    return dirtyPriceSensitivity(security, provider, settlementDate);
  }