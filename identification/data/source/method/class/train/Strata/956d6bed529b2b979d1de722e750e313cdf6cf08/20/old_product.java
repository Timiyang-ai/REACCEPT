public PointSensitivityBuilder presentValueSensitivity(
      CapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      double cleanRealPrice) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    PointSensitivityBuilder productSensi = productPricer.presentValueSensitivity(trade.getProduct(),
        ratesProvider, issuerDiscountFactorsProvider, settlementDate).multipliedBy(trade.getQuantity());
    PointSensitivityBuilder settleSensi =
        presentValueSensitivityFromRealCleanPrice(trade, ratesProvider, issuerDiscountFactorsProvider, cleanRealPrice);
    return productSensi.combinedWith(settleSensi);
  }