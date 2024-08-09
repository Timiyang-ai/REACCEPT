public PointSensitivityBuilder presentValueSensitivity(SwapLeg leg, RatesProvider provider) {
    ExpandedSwapLeg expanded = leg.expand();
    return legValueSensitivity(
        expanded,
        provider,
        paymentPeriodPricer::presentValueSensitivity,
        paymentEventPricer::presentValueSensitivity);
  }