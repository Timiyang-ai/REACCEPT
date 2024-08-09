@Override
  public CurrencyAmount convertedTo(Currency resultCurrency, FxRateProvider rateProvider) {
    if (amounts.size() == 1) {
      return amounts.first().convertedTo(resultCurrency, rateProvider);
    }
    double total = 0d;
    for (CurrencyAmount amount : amounts) {
      total += rateProvider.convert(amount.getAmount(), amount.getCurrency(), resultCurrency);
    }
    return CurrencyAmount.of(resultCurrency, total);
  }