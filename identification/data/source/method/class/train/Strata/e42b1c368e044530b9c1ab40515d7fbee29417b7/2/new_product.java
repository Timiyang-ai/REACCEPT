public CurrencyAmount negated() {
    // Zero is treated as a special case to avoid creating -0.0 which produces surprising equality behaviour
    return new CurrencyAmount(currency, amount == 0d ? 0d : -amount);
  }