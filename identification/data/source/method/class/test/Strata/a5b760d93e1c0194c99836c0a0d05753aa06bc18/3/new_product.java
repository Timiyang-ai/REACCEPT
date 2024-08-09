public PointSensitivityBuilder forecastValueSensitivity(ResolvedSwapLeg leg, RatesProvider provider) {
    return legValueSensitivity(
        leg,
        provider,
        paymentPeriodPricer::forecastValueSensitivity,
        paymentEventPricer::forecastValueSensitivity);
  }