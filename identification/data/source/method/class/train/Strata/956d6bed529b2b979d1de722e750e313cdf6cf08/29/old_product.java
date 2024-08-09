public PointSensitivityBuilder presentValueSensitivityWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    PointSensitivityBuilder productSensi = productPricer.presentValueSensitivityWithZSpread(trade.getProduct(),
        ratesProvider, issuerDiscountFactorsProvider, settlementDate, zSpread, compoundedRateType, periodsPerYear);
    return presentValueSensitivityFromProductPresentValueSensitivity(
        trade, ratesProvider, issuerDiscountFactorsProvider, productSensi);
  }