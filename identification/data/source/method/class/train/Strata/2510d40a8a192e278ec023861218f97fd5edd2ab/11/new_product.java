public PointSensitivityBuilder presentValueSensitivity(RatesProvider provider, SwapProduct product) {
    return swapValueSensitivity(
        provider,
        product.expand(),
        legPricer::presentValueSensitivity);
  }