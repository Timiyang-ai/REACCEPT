public double parRate(ResolvedTermDeposit deposit, RatesProvider provider) {
    Currency currency = deposit.getCurrency();
    DiscountFactors discountFactors = provider.discountFactors(currency);
    double dfStart = discountFactors.discountFactor(deposit.getStartDate());
    double dfEnd = discountFactors.discountFactor(deposit.getEndDate());
    double accrualFactor = deposit.getYearFraction();
    return (dfStart / dfEnd - 1d) / accrualFactor;
  }