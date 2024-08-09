public static <R> Result<R> success(R value) {
    return new Result<>(ArgChecker.notNull(value, "value"));
  }