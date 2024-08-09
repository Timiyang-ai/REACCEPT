public CurrencyAmount presentValue(
      ResolvedBondFutureOptionTrade trade,
      LegalEntityDiscountingProvider ratesProvider,
      BlackBondFutureVolatilities volatilities,
      double futurePrice,
      double lastOptionSettlementPrice) {

    double optionPrice = productPricer.price(trade.getProduct(), ratesProvider, volatilities, futurePrice);
    return presentValue(trade, ratesProvider.getValuationDate(), optionPrice, lastOptionSettlementPrice);
  }