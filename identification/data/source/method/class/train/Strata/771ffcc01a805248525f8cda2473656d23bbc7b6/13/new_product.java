public CurrencyAmount presentValue(IborFixingDepositProduct product, RatesProvider provider) {
    ExpandedIborFixingDeposit deposit = product.expand();
    if (provider.getValuationDate().isAfter(deposit.getEndDate())) {
      return CurrencyAmount.of(deposit.getCurrency(), 0.0d);
    }
    double forwardRate = forwardRate(deposit, provider);
    double discountFactor = provider.discountFactor(deposit.getCurrency(), deposit.getEndDate());
    double pv = discountFactor * deposit.getNotional() * deposit.getYearFraction() * (deposit.getRate() - forwardRate);
    return CurrencyAmount.of(deposit.getCurrency(), pv);
  }