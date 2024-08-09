public PointSensitivities parRateSensitivity(ResolvedTermDeposit deposit, RatesProvider provider) {
    return parSpreadSensitivity(deposit, provider);
  }