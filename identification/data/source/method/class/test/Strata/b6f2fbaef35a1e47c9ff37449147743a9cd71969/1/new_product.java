public static <R> Result<R> ofNullable(
      R value,
      FailureReason reason,
      String message,
      Object... messageArgs) {

    if (value != null) {
      return success(value);
    } else {
      return failure(reason, message, messageArgs);
    }
  }