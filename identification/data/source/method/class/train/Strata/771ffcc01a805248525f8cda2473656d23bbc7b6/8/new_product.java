public CurrencyAmount presentValue(IborFixingDepositProduct product, RatesProvider provider) {
    ExpandedIborFixingDeposit deposit = product.expand();
    Currency currency = deposit.getCurrency();
    if (provider.getValuationDate().isAfter(deposit.getEndDate())) {
      return CurrencyAmount.of(currency, 0.0d);
    }
    double forwardRate = forwardRate(deposit, provider);
    double discountFactor = provider.discountFactor(currency, deposit.getEndDate());
    double fv = deposit.getNotional() * deposit.getYearFraction() * (deposit.getFixedRate() - forwardRate);
    double pv = discountFactor * fv;
    return CurrencyAmount.of(currency, pv);
  }