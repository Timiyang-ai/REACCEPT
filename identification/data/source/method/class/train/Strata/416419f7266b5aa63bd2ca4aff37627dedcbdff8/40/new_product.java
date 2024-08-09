public CurrencyAmount presentValue(
      ResolvedBondFutureOptionTrade trade,
      LegalEntityDiscountingProvider discountingProvider,
      BlackBondFutureVolatilities volatilities,
      double futurePrice,
      double lastOptionSettlementPrice) {

    double optionPrice = productPricer.price(trade.getProduct(), discountingProvider, volatilities, futurePrice);
    return presentValue(trade, discountingProvider.getValuationDate(), optionPrice, lastOptionSettlementPrice);
  }