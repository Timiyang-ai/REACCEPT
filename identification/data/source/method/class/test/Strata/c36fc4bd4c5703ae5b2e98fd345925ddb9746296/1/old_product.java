public default double convert(double amount, Currency fromCurrency, Currency toCurrency, int scenarioIndex) {
    return amount * fxRate(fromCurrency, toCurrency, scenarioIndex);
  }