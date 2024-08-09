public PointSensitivities parSpreadSensitivity(ResolvedIborFixingDeposit deposit, RatesProvider provider) {
    return forwardRateSensitivity(deposit, provider).build();
  }