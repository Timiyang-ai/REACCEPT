public MultiCurrencyAmount negated() {
    // Zero is treated as a special case to avoid creating -0.0 which produces surprising equality behaviour
    return mapAmounts(a -> a == 0d ? 0d : -a);
  }