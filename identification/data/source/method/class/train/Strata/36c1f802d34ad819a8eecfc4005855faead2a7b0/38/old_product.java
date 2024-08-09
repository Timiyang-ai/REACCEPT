public double dirtyPriceFromCurvesWithZSpread(
      Security<FixedCouponBond> security,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    FixedCouponBond product = security.getProduct();
    LocalDate settlementDate = product.getSettlementDateOffset().adjust(provider.getValuationDate());
    return dirtyPriceFromCurvesWithZSpread(security, provider, zSpread, compoundedRateType, periodsPerYear, settlementDate);
  }