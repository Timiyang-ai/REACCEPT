public PointSensitivityBuilder forecastValueSensitivity(SwapProduct product, RatesProvider provider) {
    return swapValueSensitivity(
        product.expand(),
        provider,
        legPricer::forecastValueSensitivity);
  }