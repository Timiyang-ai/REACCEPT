public MultiCurrencyAmount currencyExposure(ResolvedFxSingle product, RatesProvider provider) {
    return presentValue(product, provider);
  }