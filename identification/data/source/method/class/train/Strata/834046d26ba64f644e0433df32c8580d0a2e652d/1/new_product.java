public CurrencyParameterSensitivity diagonal() {
    CrossGammaParameterSensitivity blockDiagonal = getSensitivity(getMarketDataName());
    int size = getParameterCount();
    return CurrencyParameterSensitivity.of(
        getMarketDataName(),
        getParameterMetadata(),
        getCurrency(),
        DoubleArray.of(size, i -> blockDiagonal.getSensitivity().get(i, i)));
  }