@VisibleForTesting
  static TypedValue createTypedValue(Value value) {
    return value.match(
        typedValueDoubleFunction,
        typedValueLongFunction,
        typedValueDistributionFunction,
        typedValueSummaryFunction,
        Functions.<TypedValue>throwIllegalArgumentException());
  }