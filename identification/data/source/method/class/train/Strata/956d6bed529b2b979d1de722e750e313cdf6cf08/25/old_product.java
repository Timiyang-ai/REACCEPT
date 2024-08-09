public PointSensitivities presentValueSensitivity(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider) {

    validate(ratesProvider, discountingProvider);
    LocalDate settlementDate = trade.getSettlementDate();
    PointSensitivityBuilder productSensi = productPricer.presentValueSensitivity(trade.getProduct(),
        ratesProvider, discountingProvider, settlementDate);
    return presentValueSensitivityFromProductPresentValueSensitivity(
        trade, ratesProvider, discountingProvider, productSensi).build();
  }