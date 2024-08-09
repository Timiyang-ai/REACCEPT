public PointSensitivityBuilder presentValueSensitivity(ResolvedSwapLeg leg, RatesProvider provider) {
    return legValueSensitivity(
        leg,
        provider,
        paymentPeriodPricer::presentValueSensitivity,
        paymentEventPricer::presentValueSensitivity);
  }