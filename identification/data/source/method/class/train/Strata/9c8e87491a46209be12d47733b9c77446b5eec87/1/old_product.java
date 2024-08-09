@Override
  public MultiCurrencyAmount currencyExposure(NotionalExchange event, RatesProvider provider) {
    return MultiCurrencyAmount.of(CurrencyAmount.of(event.getCurrency(), presentValue(event, provider)));
  }