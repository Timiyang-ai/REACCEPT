public PointSensitivityBuilder presentValueSensitivity(ResolvedSwap swap, RatesProvider provider) {
    return swapValueSensitivity(swap, provider, legPricer::presentValueSensitivity);
  }