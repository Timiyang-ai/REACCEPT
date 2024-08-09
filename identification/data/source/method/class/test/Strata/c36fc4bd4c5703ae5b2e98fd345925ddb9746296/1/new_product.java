public default double convert(double amount, Currency fromCurrency, Currency toCurrency, int scenarioIndex) {
    if (fromCurrency.equals(toCurrency)) {
      return amount;
    }
    return amount * fxRate(fromCurrency, toCurrency, scenarioIndex);
  }