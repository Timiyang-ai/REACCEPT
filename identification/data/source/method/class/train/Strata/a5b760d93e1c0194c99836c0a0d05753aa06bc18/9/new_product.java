public MultiCurrencyAmount currencyExposure(ResolvedSwapLeg leg, RatesProvider provider) {
    return currencyExposurePeriodsInternal(leg, provider).plus(currencyExposureEventsInternal(leg, provider));
  }