public PointSensitivityBuilder presentValueSensitivity(RatesProvider provider, SwapLeg leg) {
    ExpandedSwapLeg expanded = leg.expand();
    return legValueSensitivity(
        provider,
        expanded,
        paymentPeriodPricer::presentValueSensitivity,
        paymentEventPricer::presentValueSensitivity);
  }