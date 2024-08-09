public double parSpread(
      ResolvedIborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider,
      double lastSettlementPrice) {

    double referencePrice = referencePrice(trade, ratesProvider.getValuationDate(), lastSettlementPrice);
    return price(trade, ratesProvider, hwProvider) - referencePrice;
  }