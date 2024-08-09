public CurrencyAmount presentValueWithZSpread(
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
    CurrencyAmount pvProduct = productPricer.presentValueWithZSpread(
        trade.getProduct(), ratesProvider,
        issuerDiscountFactorsProvider,
        settlementDate,
        zSpread,
        compoundedRateType,
        periodsPerYear);
    CurrencyAmount pvSettle = presentValueFromCleanPriceWithZSpread(
        trade,
        ratesProvider,
        issuerDiscountFactorsProvider,
        refData,
        cleanRealPrice,
        zSpread,
        compoundedRateType,
        periodsPerYear);
    return pvProduct.multipliedBy(trade.getQuantity()).plus(pvSettle);
  }