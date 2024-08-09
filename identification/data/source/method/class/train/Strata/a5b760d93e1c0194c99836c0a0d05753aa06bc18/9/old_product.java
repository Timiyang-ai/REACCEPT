public MultiCurrencyAmount currencyExposure(SwapLeg leg, RatesProvider provider) {
    ExpandedSwapLeg expanded = leg.expand();
    return currencyExposurePeriodsInternal(expanded, provider).plus(currencyExposureEventsInternal(expanded, provider));
  }