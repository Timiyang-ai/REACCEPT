public static <R> Result<R> failure(Exception exception, String message, Object... messageArgs) {
    return new Result<>(Failure.of(FailureReason.ERROR, exception, message, messageArgs));
  }