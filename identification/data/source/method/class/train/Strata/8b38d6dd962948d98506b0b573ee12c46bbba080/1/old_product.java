public double parSpread(
      ResolvedIborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider,
      double lastMarginPrice) {

    double referencePrice = referencePrice(trade, ratesProvider.getValuationDate(), lastMarginPrice);
    return price(trade, ratesProvider, hwProvider) - referencePrice;
  }