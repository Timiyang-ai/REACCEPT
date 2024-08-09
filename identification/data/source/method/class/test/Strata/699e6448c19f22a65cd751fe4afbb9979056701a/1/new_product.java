@Override
  public MultiCurrencyAmount currencyExposure(KnownAmountSwapPaymentPeriod period, RatesProvider provider) {
    return MultiCurrencyAmount.of(CurrencyAmount.of(period.getCurrency(), presentValue(period, provider)));
  }