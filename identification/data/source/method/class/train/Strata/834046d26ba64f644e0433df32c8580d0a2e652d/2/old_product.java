public CrossGammaParameterSensitivity withSensitivity(DoubleMatrix sensitivity) {
    return new CrossGammaParameterSensitivity(marketDataName, parameterMetadata, currency, sensitivity);
  }