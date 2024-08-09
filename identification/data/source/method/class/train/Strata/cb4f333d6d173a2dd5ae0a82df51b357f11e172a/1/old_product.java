public UnitParameterSensitivity withSensitivity(DoubleArray sensitivity) {
    return new UnitParameterSensitivity(marketDataName, parameterMetadata, sensitivity);
  }