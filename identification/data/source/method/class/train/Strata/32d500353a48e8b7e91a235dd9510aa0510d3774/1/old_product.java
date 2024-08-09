@Override
  public CurrencyAmount convertedTo(Currency resultCurrency, FxRateProvider rateProvider) {
    if (currency.equals(resultCurrency)) {
      return this;
    }
    double fxRate = rateProvider.fxRate(currency, resultCurrency);
    return CurrencyAmount.of(resultCurrency, amount * fxRate);
  }