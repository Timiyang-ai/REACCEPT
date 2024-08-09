public MultiCurrencyAmount currencyExposure(
      ResolvedIborFutureTrade trade,
      RatesProvider provider,
      HullWhiteOneFactorPiecewiseConstantParametersProvider hwProvider,
      double lastSettlementPrice) {

    return MultiCurrencyAmount.of(presentValue(trade, provider, hwProvider, lastSettlementPrice));
  }