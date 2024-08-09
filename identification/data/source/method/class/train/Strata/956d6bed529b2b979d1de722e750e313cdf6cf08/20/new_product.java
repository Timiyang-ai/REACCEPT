public PointSensitivityBuilder presentValueSensitivity(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData,
      double cleanRealPrice) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    PointSensitivityBuilder productSensi = productPricer.presentValueSensitivity(trade.getProduct(),
        ratesProvider, issuerDiscountFactorsProvider, settlementDate).multipliedBy(trade.getQuantity());
    PointSensitivityBuilder settleSensi = presentValueSensitivityFromRealCleanPrice(
        trade, ratesProvider, issuerDiscountFactorsProvider, refData, cleanRealPrice);
    return productSensi.combinedWith(settleSensi);
  }