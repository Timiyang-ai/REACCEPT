public MultiCurrencyAmount currencyExposure(
      IborFutureTrade trade,
      RatesProvider provider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider,
      double lastMarginPrice) {

    return MultiCurrencyAmount.of(presentValue(trade, provider, hwProvider, lastMarginPrice));
  }