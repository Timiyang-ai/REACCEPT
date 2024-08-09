public CurrencyAmount presentValue(IborFixingDepositProduct product, RatesProvider provider) {
    ExpandedIborFixingDeposit deposit = product.expand();
    double forwardRate = forwardRate(deposit, provider);
    double discountFactor = provider.discountFactor(deposit.getCurrency(), deposit.getEndDate());
    double pv = discountFactor * deposit.getNotional() * deposit.getYearFraction() * (deposit.getRate() - forwardRate);
    return CurrencyAmount.of(deposit.getCurrency(), pv);
  }