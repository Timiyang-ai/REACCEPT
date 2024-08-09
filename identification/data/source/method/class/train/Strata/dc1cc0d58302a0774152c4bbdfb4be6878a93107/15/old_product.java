public PointSensitivityBuilder presentValueSensitivity(PricingEnvironment env, SwapLeg leg) {
    ExpandedSwapLeg expanded = leg.expand();
    return legValueSensitivity(
        env,
        expanded,
        paymentPeriodPricer::presentValueSensitivity,
        paymentEventPricer::presentValueSensitivity);
  }