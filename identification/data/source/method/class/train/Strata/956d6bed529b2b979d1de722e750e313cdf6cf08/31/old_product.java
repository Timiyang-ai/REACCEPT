public CurrencyAmount presentValue(
      CapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider issuerDiscountFactorsProvider,
      double cleanRealPrice) {

    validate(ratesProvider, issuerDiscountFactorsProvider);
    LocalDate settlementDate = trade.getTradeInfo().getSettlementDate().get();
    CurrencyAmount pvProduct = productPricer.presentValue(trade.getProduct(), ratesProvider,
        issuerDiscountFactorsProvider, settlementDate);
    CurrencyAmount pvSettle = presentValueFromCleanPrice(trade, ratesProvider, issuerDiscountFactorsProvider,
        cleanRealPrice);
    return pvProduct.multipliedBy(trade.getQuantity()).plus(pvSettle);
  }