public MultiCurrencyAmount mapAmounts(DoubleUnaryOperator mapper) {
    ArgChecker.notNull(mapper, "mapper");
    return amounts.stream()
        .map(ca -> ca.mapAmount(mapper))
        .collect(MultiCurrencyAmount.collector());
  }