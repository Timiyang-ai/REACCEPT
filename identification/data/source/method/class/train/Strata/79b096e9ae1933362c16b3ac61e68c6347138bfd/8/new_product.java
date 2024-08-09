public static <R> Result<R> failure(Result<?> result1, Result<?> result2, Result<?>... results) {
    ArgChecker.notNull(result1, "result1");
    ArgChecker.notNull(result2, "result2");
    ArgChecker.notNull(results, "results");
    ImmutableList<Result<?>> list = ImmutableList.<Result<?>>builder()
      .add(result1)
      .add(result2)
      .addAll(Arrays.asList(results))
      .build();
    return failure(list);
  }