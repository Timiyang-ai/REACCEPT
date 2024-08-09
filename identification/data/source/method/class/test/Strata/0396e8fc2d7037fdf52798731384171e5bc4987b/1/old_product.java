public <R> ValueWithFailures<R> flatMap(Function<? super T, ValueWithFailures<R>> function) {
    ValueWithFailures<R> transformedValue = Objects.requireNonNull(function.apply(value));
    ImmutableList<FailureItem> combinedFailures = ImmutableList.<FailureItem>builder()
        .addAll(this.failures)
        .addAll(transformedValue.failures)
        .build();
    return ValueWithFailures.of(transformedValue.value, combinedFailures);
  }