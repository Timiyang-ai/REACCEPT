public PointSensitivities presentValueSensitivity(ResolvedTermDeposit deposit, RatesProvider provider) {
    Currency currency = deposit.getCurrency();
    // backward sweep
    double dfEndBar = deposit.getNotional() + deposit.getInterest();
    double dfStartBar = -initialAmount(deposit, provider);
    // sensitivity
    DiscountFactors discountFactors = provider.discountFactors(currency);
    PointSensitivityBuilder sensStart = discountFactors.zeroRatePointSensitivity(deposit.getStartDate())
        .multipliedBy(dfStartBar);
    PointSensitivityBuilder sensEnd = discountFactors.zeroRatePointSensitivity(deposit.getEndDate())
        .multipliedBy(dfEndBar);
    return sensStart.combinedWith(sensEnd).build();
  }