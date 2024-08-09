public MultiCurrencyAmount presentValue(SwapProduct product, RatesProvider provider) {
    return swapValue(provider, product.expand(), legPricer::presentValueInternal);
  }