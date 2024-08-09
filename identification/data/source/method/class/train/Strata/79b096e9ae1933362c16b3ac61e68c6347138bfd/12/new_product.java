public static <R> Result<R> failure(FailureReason reason, String message, Object... messageArgs) {
    String msg = Messages.format(message, messageArgs);
    return new Result<>(Failure.of(FailureItem.of(reason, msg, 1)));
  }