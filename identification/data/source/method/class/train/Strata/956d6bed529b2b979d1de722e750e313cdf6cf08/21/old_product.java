public PointSensitivityBuilder presentValueSensitivityWithZSpread(
      CapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
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
        trade, ratesProvider, issuerDiscountFactorsProvider, cleanRealPrice, zSpread, compoundedRateType, periodsPerYear);
    return productSensi.combinedWith(settleSensi);
  }