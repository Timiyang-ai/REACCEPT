public CurrencyAmount presentValue(
      ResolvedBondFutureOptionTrade trade,
      LegalEntityDiscountingProvider ratesProvider,
      BlackVolatilityBondFutureProvider volatilityProvider,
      double futurePrice,
      double lastClosingPrice) {

    double optionPrice = getProductPricer().price(trade.getProduct(), ratesProvider, volatilityProvider, futurePrice);
    return presentValue(trade, ratesProvider.getValuationDate(), optionPrice, lastClosingPrice);
  }