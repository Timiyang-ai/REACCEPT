public MultiCurrencyAmount currencyExposure(
      ResolvedDsfTrade trade,
      RatesProvider provider,
      double referencePrice) {

    return MultiCurrencyAmount.of(presentValue(trade, provider, referencePrice));
  }