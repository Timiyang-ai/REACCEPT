public static <R> Result<R> failure(Exception exception, String message, Object... messageArgs) {
    String msg = ArgChecker.formatMessage(message, messageArgs);
    return new Result<>(null, Failure.of(FailureReason.ERROR, msg, exception));
  }