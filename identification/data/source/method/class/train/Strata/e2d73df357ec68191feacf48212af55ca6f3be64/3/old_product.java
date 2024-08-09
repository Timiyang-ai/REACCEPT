public PointSensitivityBuilder presentValueSensitivity(
      ResolvedFixedCouponBondTrade trade,
      LegalEntityDiscountingProvider provider) {

    LocalDate settlementDate = trade.getSettlementDate();
    PointSensitivityBuilder sensiProduct = productPricer.presentValueSensitivity(
        trade.getProduct(), provider, settlementDate);
    return presentValueSensitivityFromProductPresentValueSensitivity(trade, provider, sensiProduct);
  }