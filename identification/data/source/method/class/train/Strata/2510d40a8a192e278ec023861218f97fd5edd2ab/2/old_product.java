public PointSensitivityBuilder presentValueSensitivity(SwapProduct product, RatesProvider provider) {
    return swapValueSensitivity(
        product.expand(),
        provider,
        legPricer::presentValueSensitivity);
  }