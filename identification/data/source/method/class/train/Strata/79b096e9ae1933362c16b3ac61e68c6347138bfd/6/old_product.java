public static <R> Result<R> failure(FailureReason reason, String message, Object... messageArgs) {
    String msg = ArgChecker.formatMessage(message, messageArgs);
    return new Result<>(null, Failure.of(reason, msg));
  }