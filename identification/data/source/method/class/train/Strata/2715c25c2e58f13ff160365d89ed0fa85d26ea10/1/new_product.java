public <U, R> ValueWithFailures<R> combinedWith(ValueWithFailures<U> other, BiFunction<T, U, R> combiner) {
    R combinedValue = Objects.requireNonNull(combiner.apply(value, other.value));
    ImmutableList<FailureItem> combinedFailures = concatToList(failures, other.failures);
    return ValueWithFailures.of(combinedValue, combinedFailures);
  }