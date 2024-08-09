public CurrencyAmount calculateMonetaryAmount(double quantity, double price) {
    return CurrencyAmount.of(tickValue.getCurrency(), calculateMonetaryValue(quantity, price));
  }