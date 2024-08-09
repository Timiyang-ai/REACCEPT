@Override
  public MultiCurrencyAmount currencyExposure(KnownAmountPaymentPeriod period, RatesProvider provider) {
    return MultiCurrencyAmount.of(CurrencyAmount.of(period.getCurrency(), presentValue(period, provider)));
  }