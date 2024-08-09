public PointSensitivities presentValueSensitivity(IborFixingDepositProduct product, RatesProvider provider) {
    ExpandedIborFixingDeposit deposit = product.expand();
    double forwardRate = forwardRate(deposit, provider);
    DiscountFactors discountFactors = provider.discountFactors(deposit.getCurrency());
    double discountFactor = discountFactors.discountFactor(deposit.getEndDate());
    PointSensitivityBuilder sensiFwd = forwardRateSensitivity(deposit, provider)
        .multipliedBy(-discountFactor * deposit.getNotional() * deposit.getYearFraction());
    PointSensitivityBuilder sensiDsc = discountFactors.pointSensitivity(deposit.getEndDate())
        .multipliedBy(deposit.getNotional() * deposit.getYearFraction() * (deposit.getRate() - forwardRate));
    return sensiFwd.combinedWith(sensiDsc).build();
  }