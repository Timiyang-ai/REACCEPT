public MultiCurrencyAmount currencyExposure(FraTrade trade, RatesProvider provider) {
    return MultiCurrencyAmount.of(presentValue(trade, provider));
  }