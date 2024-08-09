public MultiCurrencyAmount presentValue(ResolvedSwap swap, RatesProvider provider) {
    return swapValue(provider, swap, legPricer::presentValueInternal);
  }