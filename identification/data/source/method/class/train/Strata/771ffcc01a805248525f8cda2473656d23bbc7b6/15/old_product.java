public CurrencyAmount presentValue(TermDepositProduct product, RatesProvider provider) {
    ExpandedTermDeposit deposit = product.expand();
    Currency currency = deposit.getCurrency();
    if (provider.getValuationDate().isAfter(deposit.getEndDate())) {
      return CurrencyAmount.of(currency, 0.0d);
    }
    DiscountFactors discountFactors = provider.discountFactors(currency);
    double dfStart = discountFactors.discountFactor(deposit.getStartDate());
    double dfEnd = discountFactors.discountFactor(deposit.getEndDate());
    double pvStart = initialAmount(deposit, provider) * dfStart;
    double pvEnd = (deposit.getNotional() + deposit.getInterest()) * dfEnd;
    double pv = pvEnd - pvStart;
    return CurrencyAmount.of(currency, pv);
  }