public CurrencyAmount presentValue(
      ResolvedBondFutureOptionTrade trade,
      LegalEntityDiscountingProvider discountingProvider,
      BondFutureVolatilities volatilities,
      double lastOptionSettlementPrice) {

    double price = price(trade, discountingProvider, volatilities);
    return presentValue(trade, discountingProvider.getValuationDate(), price, lastOptionSettlementPrice);
  }