public CurrencyAmount calculateMonetaryValue(long quantity, double price) {
    double value = price * quantity * multiplier;
    return CurrencyAmount.of(tickValue.getCurrency(), value);
  }