public double parSpread(
      ResolvedIborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider,
      double settlementPrice) {

    double referencePrice = referencePrice(trade, ratesProvider.getValuationDate(), settlementPrice);
    return price(trade, ratesProvider, hwProvider) - referencePrice;
  }