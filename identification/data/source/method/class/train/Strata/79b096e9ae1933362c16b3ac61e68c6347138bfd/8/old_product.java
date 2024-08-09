public static <R> Result<R> failure(Result<?> result1, Result<?> result2, Result<?>... results) {
    ArgChecker.notNull(result1, "result1");
    ArgChecker.notNull(result2, "result2");
    ArgChecker.notNull(results, "results");
    ImmutableList.Builder<Result<?>> builder = ImmutableList.builder();
    builder.add(result1);
    builder.add(result2);
    builder.addAll(Arrays.asList(results));
    return failure(builder.build());
  }