public ValueWithFailures<T> combinedWith(ValueWithFailures<T> other, BinaryOperator<T> combiner) {
    T combinedValues = combiner.apply(value, other.value);
    ImmutableList<FailureItem> combinedFailures = concatToList(failures, other.failures);
    return of(combinedValues, combinedFailures);
  }