public CurrencyAmount presentValue(TermDepositProduct product, RatesProvider provider) {
    ExpandedTermDeposit deposit = product.expand();
    Currency currency = deposit.getCurrency();
    if (provider.getValuationDate().isAfter(deposit.getEndDate())) {
      return CurrencyAmount.of(currency, 0.0d);
    }
    double dfStart = provider.discountFactor(currency, deposit.getStartDate());
    double dfEnd = provider.discountFactor(currency, deposit.getEndDate());
    double pv = (deposit.getNotional() + deposit.getInterest()) * dfEnd - initialAmount(deposit, provider) * dfStart;
    return CurrencyAmount.of(currency, pv);
  }