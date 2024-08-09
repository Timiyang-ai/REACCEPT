public double dirtyPriceFromCurvesWithZSpread(
      Security<FixedCouponBond> security,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      boolean periodic,
      int periodsPerYear) {

    FixedCouponBond product = security.getProduct();
    LocalDate settlementDate = product.getSettlementDateOffset().adjust(provider.getValuationDate());
    return dirtyPriceFromCurvesWithZSpread(security, provider, zSpread, periodic, periodsPerYear, settlementDate);
  }