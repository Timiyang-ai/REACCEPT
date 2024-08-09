public PointSensitivities presentValueSensitivityWithZSpread(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider,
      double zSpread,
      CompoundedRateType compoundedRateType,
      int periodsPerYear) {

    validate(ratesProvider, discountingProvider);
    LocalDate settlementDate = trade.getSettlementDate();
    PointSensitivityBuilder productSensi = productPricer.presentValueSensitivityWithZSpread(trade.getProduct(),
        ratesProvider, discountingProvider, settlementDate, zSpread, compoundedRateType, periodsPerYear);
    return presentValueSensitivityFromProductPresentValueSensitivity(
        trade, ratesProvider, discountingProvider, productSensi).build();
  }