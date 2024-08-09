public CurrencyAmount presentValue(
      ResolvedBondFutureOptionTrade trade,
      LegalEntityDiscountingProvider ratesProvider,
      BondFutureVolatilities volatilities,
      double lastOptionSettlementPrice) {

    double price = price(trade, ratesProvider, volatilities);
    return presentValue(trade, ratesProvider.getValuationDate(), price, lastOptionSettlementPrice);
  }