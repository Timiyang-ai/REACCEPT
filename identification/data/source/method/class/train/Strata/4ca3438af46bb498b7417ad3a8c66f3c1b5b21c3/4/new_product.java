public MultiCurrencyAmount currencyExposure(ResolvedFraTrade trade, RatesProvider provider) {
    return MultiCurrencyAmount.of(presentValue(trade, provider));
  }