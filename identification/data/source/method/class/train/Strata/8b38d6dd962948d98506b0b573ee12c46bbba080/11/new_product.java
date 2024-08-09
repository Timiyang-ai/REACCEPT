public CurrencyAmount presentValue(
      ResolvedIborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider,
      double lastSettlementPrice) {

    double referencePrice = referencePrice(trade, ratesProvider.getValuationDate(), lastSettlementPrice);
    double price = price(trade, ratesProvider, hwProvider);
    return presentValue(trade, price, referencePrice);
  }