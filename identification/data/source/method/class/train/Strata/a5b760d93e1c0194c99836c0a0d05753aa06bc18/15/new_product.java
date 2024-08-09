public MultiCurrencyAmount forecastValue(ResolvedSwap swap, RatesProvider provider) {
    return swapValue(provider, swap, legPricer::forecastValueInternal);
  }