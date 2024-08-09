public PointSensitivityBuilder presentValueSensitivity(PricingEnvironment env, SwapProduct product) {
    return swapValueSensitivity(
        env,
        product.expand(),
        paymentPeriodPricer::presentValueSensitivity,
        paymentEventPricer::presentValueSensitivity);
  }