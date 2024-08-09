public static <R> Result<R> failure(FailureReason reason, Exception exception) {
    return new Result<>(null, Failure.of(reason, exception));
  }