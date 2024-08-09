public PointSensitivities parSpreadSensitivity(TermDepositProduct product, RatesProvider provider) {
    ExpandedTermDeposit deposit = product.expand();
    Currency currency = deposit.getCurrency();
    double accrualFactorInv = 1d / deposit.getYearFraction();
    double dfStart = provider.discountFactor(currency, deposit.getStartDate());
    double dfEndInv = 1d / provider.discountFactor(currency, deposit.getEndDate());
    DiscountFactors discountFactors = provider.discountFactors(currency);
    PointSensitivityBuilder sensStart = discountFactors.zeroRatePointSensitivity(deposit.getStartDate())
        .multipliedBy(dfEndInv * accrualFactorInv);
    PointSensitivityBuilder sensEnd = discountFactors.zeroRatePointSensitivity(deposit.getEndDate())
        .multipliedBy(-dfStart * dfEndInv * dfEndInv * accrualFactorInv);
    return sensStart.combinedWith(sensEnd).build();
  }