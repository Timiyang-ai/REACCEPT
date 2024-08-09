public MultiCurrencyAmount currencyExposure(
      ResolvedIborFutureTrade trade,
      RatesProvider provider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider,
      double settlementPrice) {

    return MultiCurrencyAmount.of(presentValue(trade, provider, hwProvider, settlementPrice));
  }