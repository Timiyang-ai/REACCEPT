public MultiCurrencyAmount forecastValue(SwapProduct product, RatesProvider provider) {
    return swapValue(provider, product.expand(), legPricer::forecastValueInternal);
  }