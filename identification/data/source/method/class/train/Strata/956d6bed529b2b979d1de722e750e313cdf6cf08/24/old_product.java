public PointSensitivityBuilder presentValueSensitivityWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      ReferenceData refData,
      double cleanRealPrice,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    PointSensitivityBuilder productSensi = productPricer.presentValueSensitivityWithZSpread(trade.getProduct(),
        ratesProvider, issuerDiscountFactorsProvider, settlementDate, zSpread, compoundedRateType, periodsPerYear)
        .multipliedBy(trade.getQuantity());
    PointSensitivityBuilder settleSensi = presentValueSensitivityFromRealCleanPriceWithZSpread(
        trade,
        ratesProvider,
        issuerDiscountFactorsProvider,
        refData,
        cleanRealPrice,
        zSpread,
        compoundedRateType,
        periodsPerYear);
    return productSensi.combinedWith(settleSensi);
  }