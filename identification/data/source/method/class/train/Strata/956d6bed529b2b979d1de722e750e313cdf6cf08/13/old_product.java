public CurrencyAmount presentValue(
      ResolvedCapitalIndexedBondTrade trade,
      RatesProvider ratesProvider,
      LegalEntityDiscountingProvider discountingProvider) {

    validate(ratesProvider, discountingProvider);
    LocalDate settlementDate = trade.getSettlementDate();
    CurrencyAmount pvProduct = productPricer.presentValue(trade.getProduct(), ratesProvider,
        discountingProvider, settlementDate);
    return presentValueFromProductPresentValue(trade, ratesProvider, discountingProvider, pvProduct);
  }