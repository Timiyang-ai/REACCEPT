public PointSensitivityBuilder forecastValueSensitivity(ResolvedSwap swap, RatesProvider provider) {
    return swapValueSensitivity(swap, provider, legPricer::forecastValueSensitivity);
  }