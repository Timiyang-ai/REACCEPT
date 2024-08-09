public CurrencyAmount presentValue(
      ResolvedIborFutureTrade trade,
      RatesProvider ratesProvider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider,
      double settlementPrice) {

    double referencePrice = referencePrice(trade, ratesProvider.getValuationDate(), settlementPrice);
    double price = price(trade, ratesProvider, hwProvider);
    return presentValue(trade, price, referencePrice);
  }