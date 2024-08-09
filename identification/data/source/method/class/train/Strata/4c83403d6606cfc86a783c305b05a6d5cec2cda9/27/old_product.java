public PointSensitivities presentValueSensitivity(FxSwapProduct product, RatesProvider provider) {
    ExpandedFxSwap fx = product.expand();
    PointSensitivities nearSens = fxPricer.presentValueSensitivity(fx.getNearLeg(), provider);
    PointSensitivities farSens = fxPricer.presentValueSensitivity(fx.getFarLeg(), provider);
    return nearSens.combinedWith(farSens);
  }