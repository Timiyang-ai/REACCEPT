public PointSensitivities parSpreadSensitivity(ResolvedFra fra, RatesProvider provider) {
    return forwardRateSensitivity(fra, provider).build();
  }