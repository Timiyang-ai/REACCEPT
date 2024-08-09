public MultiCurrencyAmount currencyExposure(FxSingleProduct product, RatesProvider provider) {
    return presentValue(product, provider);
  }