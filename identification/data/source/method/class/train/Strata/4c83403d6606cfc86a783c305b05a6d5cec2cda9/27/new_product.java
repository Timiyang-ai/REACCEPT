public PointSensitivities presentValueSensitivity(ResolvedFxSwap swap, RatesProvider provider) {
    PointSensitivities nearSens = fxPricer.presentValueSensitivity(swap.getNearLeg(), provider);
    PointSensitivities farSens = fxPricer.presentValueSensitivity(swap.getFarLeg(), provider);
    return nearSens.combinedWith(farSens);
  }