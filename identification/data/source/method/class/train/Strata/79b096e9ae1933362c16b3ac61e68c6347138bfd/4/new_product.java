public static <R> Result<R> failure(FailureReason reason, Exception exception, String message, Object... messageArgs) {
    return new Result<>(Failure.of(reason, exception, message, messageArgs));
  }