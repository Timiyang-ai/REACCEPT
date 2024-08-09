public static <R> Result<R> failure(Iterable<? extends Result<?>> results) {
    ArgChecker.notEmpty(results, "results");
    ImmutableSet<FailureItem> items = Guavate.stream(results)
        .filter(Result::isFailure)
        .map(Result::getFailure)
        .flatMap(f -> f.getItems().stream())
        .collect(Guavate.toImmutableSet());
    if (items.isEmpty()) {
      throw new IllegalArgumentException("All results were successes");
    }
    return new Result<>(Failure.of(items));
  }