@Override
  public CurrencyAmount convertedTo(Currency resultCurrency, FxRateProvider rateProvider) {
    if (currency.equals(resultCurrency)) {
      return this;
    }
    double converted = rateProvider.convert(amount, currency, resultCurrency);
    return CurrencyAmount.of(resultCurrency, converted);
  }