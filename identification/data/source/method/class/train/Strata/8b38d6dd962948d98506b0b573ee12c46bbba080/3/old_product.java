public CurrencyAmount presentValue(
      IborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantConvexityFactorProvider hwProvider,
      double lastMarginPrice) {
    double referencePrice = referencePrice(trade, ratesProvider.getValuationDate(), lastMarginPrice);
    double price = price(trade, ratesProvider, hwProvider);
    return presentValue(trade, price, referencePrice);
  }