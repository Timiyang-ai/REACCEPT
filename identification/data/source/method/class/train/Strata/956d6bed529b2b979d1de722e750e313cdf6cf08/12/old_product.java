public PointSensitivityBuilder presentValueSensitivity(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = trade.getSettlementDate();
    PointSensitivityBuilder productSensi = productPricer.presentValueSensitivity(trade.getProduct(),
        ratesProvider, issuerDiscountFactorsProvider, settlementDate);
    return presentValueSensitivityFromProductPresentValueSensitivity(
        trade, ratesProvider, issuerDiscountFactorsProvider, productSensi);
  }