public PointSensitivities parSpreadSensitivity(IborFixingDepositProduct product, ImmutableRatesProvider provider) {
    ExpandedIborFixingDeposit deposit = product.expand();
    return forwardRateSensitivity(deposit, provider).build();
  }