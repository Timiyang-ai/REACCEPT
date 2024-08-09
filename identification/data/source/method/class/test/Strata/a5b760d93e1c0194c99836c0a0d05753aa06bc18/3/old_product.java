public PointSensitivityBuilder forecastValueSensitivity(SwapLeg leg, RatesProvider provider) {
    ExpandedSwapLeg expanded = leg.expand();
    return legValueSensitivity(
        expanded,
        provider,
        paymentPeriodPricer::forecastValueSensitivity,
        paymentEventPricer::forecastValueSensitivity);
  }