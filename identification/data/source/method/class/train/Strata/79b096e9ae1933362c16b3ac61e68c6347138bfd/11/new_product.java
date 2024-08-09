public static <R> Result<R> failure(Exception exception) {
    return new Result<>(Failure.of(FailureReason.ERROR, exception));
  }