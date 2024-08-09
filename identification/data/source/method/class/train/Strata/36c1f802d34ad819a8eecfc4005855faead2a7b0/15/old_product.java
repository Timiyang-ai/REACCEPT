public CurrencyAmount presentValueWithZSpread(
      FixedCouponBondTrade trade,
      LegalEntityDiscountingProvider provider,
      double zSpread,
      boolean periodic,
      int periodsPerYear) {

    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    CurrencyAmount pvProduct = productPricer.presentValueWithZSpread(
        trade.getProduct(), provider, zSpread, periodic, periodsPerYear, settlementDate);
    return presentValueFromProductPresentValue(trade, provider, pvProduct);
  }