public PointSensitivities presentValueSensitivity(TermDepositProduct product, RatesProvider provider) {
    ExpandedTermDeposit deposit = product.expand();
    Currency currency = deposit.getCurrency();
    // backward sweep
    double dfEndBar = deposit.getNotional() + deposit.getInterest();
    double dfStartBar = -initialAmount(deposit, provider);
    // sensitivity
    DiscountFactors discountFactors = provider.discountFactors(currency);
    PointSensitivityBuilder sensStart = discountFactors.pointSensitivity(deposit.getStartDate())
        .multipliedBy(dfStartBar);
    PointSensitivityBuilder sensEnd = discountFactors.pointSensitivity(deposit.getEndDate())
        .multipliedBy(dfEndBar);
    return sensStart.combinedWith(sensEnd).build();
  }