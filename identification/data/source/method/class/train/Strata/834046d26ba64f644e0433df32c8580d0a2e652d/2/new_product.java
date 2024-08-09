public CrossGammaParameterSensitivity withSensitivity(DoubleMatrix sensitivity) {
    return new CrossGammaParameterSensitivity(marketDataName, parameterMetadata, order, currency, sensitivity);
  }