public PointSensitivityBuilder presentValueSensitivity(PricingEnvironment env, SwapProduct product) {
    return swapValueSensitivity(
        env,
        product.expand(),
        legPricer::presentValueSensitivity);
  }