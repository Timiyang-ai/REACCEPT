public PointSensitivityBuilder presentValueSensitivity(
      FixedCouponBondTrade trade,
      LegalEntityDiscountingProvider provider) {

    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    PointSensitivityBuilder sensiProduct = productPricer.presentValueSensitivity(
        trade.getProduct(), provider, settlementDate);
    return presnetValueSensitivityFromProductPresentValueSensitivity(trade, provider, sensiProduct);
  }