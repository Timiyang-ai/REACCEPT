public CurrencyParameterSensitivity withSensitivity(DoubleArray sensitivity) {
    return new CurrencyParameterSensitivity(marketDataName, parameterMetadata, currency, sensitivity);
  }