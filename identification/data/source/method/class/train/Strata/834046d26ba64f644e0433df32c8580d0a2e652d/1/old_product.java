public CurrencyParameterSensitivity diagonal() {
    int size = getParameterCount();
    return CurrencyParameterSensitivity.of(
        getMarketDataName(), getParameterMetadata(), getCurrency(), DoubleArray.of(size, i -> sensitivity.get(i, i)));
  }