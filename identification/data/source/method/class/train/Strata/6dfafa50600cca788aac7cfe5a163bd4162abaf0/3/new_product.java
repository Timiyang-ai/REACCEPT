public MultiCurrencyAmount currencyExposure(
      ResolvedDsfTrade trade,
      RatesProvider ratesProvider,
      double lastSettlementPrice) {

    return MultiCurrencyAmount.of(presentValue(trade, ratesProvider, lastSettlementPrice));
  }