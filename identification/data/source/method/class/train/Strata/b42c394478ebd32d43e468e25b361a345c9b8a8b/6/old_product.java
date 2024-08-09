public CurrencyAmount calculateMonetaryAmount(long quantity, double price) {
    return CurrencyAmount.of(tickValue.getCurrency(), calculateMonetaryValue(quantity, price));
  }