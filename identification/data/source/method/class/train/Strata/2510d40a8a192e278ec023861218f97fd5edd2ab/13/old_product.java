public MultiCurrencyAmount presentValue(RatesProvider provider, SwapProduct product) {
    return swapValue(provider, product.expand(), legPricer::presentValueInternal);
  }