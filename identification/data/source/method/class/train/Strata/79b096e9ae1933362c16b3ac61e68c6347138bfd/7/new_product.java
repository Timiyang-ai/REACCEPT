public static <R> Result<R> failure(FailureReason reason, Exception exception) {
    return new Result<>(Failure.of(reason, exception));
  }