public double parSpread(
      IborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantConvexityFactorProvider hwProvider,
      double lastMarginPrice) {
    double referencePrice = referencePrice(trade, ratesProvider.getValuationDate(), lastMarginPrice);
    return price(trade, ratesProvider, hwProvider) - referencePrice;
  }