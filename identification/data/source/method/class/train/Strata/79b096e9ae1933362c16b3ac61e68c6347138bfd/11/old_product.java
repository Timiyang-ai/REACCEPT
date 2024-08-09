public static <R> Result<R> failure(Exception exception) {
    return new Result<>(null, Failure.of(FailureReason.ERROR, exception));
  }