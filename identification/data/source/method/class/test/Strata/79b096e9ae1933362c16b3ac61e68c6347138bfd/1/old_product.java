public static <R> Result<R> failure(Exception exception, String message, Object... messageArgs) {
    String msg = Messages.format(message, messageArgs);
    return new Result<>(Failure.of(FailureReason.ERROR, msg, exception));
  }