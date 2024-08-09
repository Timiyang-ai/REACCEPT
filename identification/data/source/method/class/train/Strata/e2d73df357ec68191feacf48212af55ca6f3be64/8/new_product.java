public PointSensitivityBuilder presentValueSensitivityWithZSpread(
      ResolvedFixedCouponBondTrade trade,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    LocalDate settlementDate = trade.getInfo().getSettlementDate().get();
    PointSensitivityBuilder sensiProduct = productPricer.presentValueSensitivityWithZSpread(
        trade.getProduct(), provider, zSpread, compoundedRateType, periodsPerYear, settlementDate);
    return presentValueSensitivityFromProductPresentValueSensitivity(trade, provider, sensiProduct);
  }