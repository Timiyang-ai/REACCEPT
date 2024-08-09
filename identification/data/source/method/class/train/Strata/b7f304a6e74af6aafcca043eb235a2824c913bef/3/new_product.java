public PointSensitivities parSpreadSensitivity(IborFixingDepositProduct product, RatesProvider provider) {
    ExpandedIborFixingDeposit deposit = product.expand();
    return forwardRateSensitivity(deposit, provider).build();
  }