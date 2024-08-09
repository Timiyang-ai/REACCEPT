public double parRate(TermDepositProduct product, RatesProvider provider) {
    ExpandedTermDeposit deposit = product.expand();
    Currency currency = deposit.getCurrency();
    double dfStart = provider.discountFactor(currency, deposit.getStartDate());
    double dfEnd = provider.discountFactor(currency, deposit.getEndDate());
    double accrualFactor = deposit.getYearFraction();
    return (dfStart / dfEnd - 1d) / accrualFactor;
  }