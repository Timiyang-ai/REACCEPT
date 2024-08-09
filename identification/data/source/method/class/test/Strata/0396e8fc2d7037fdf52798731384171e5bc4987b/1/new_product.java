public <R> ValueWithFailures<R> flatMap(Function<? super T, ValueWithFailures<R>> function) {
    ValueWithFailures<R> transformedValue = Objects.requireNonNull(function.apply(value));
    ImmutableList<FailureItem> combinedFailures = concatToList(failures, transformedValue.failures);
    return ValueWithFailures.of(transformedValue.value, combinedFailures);
  }