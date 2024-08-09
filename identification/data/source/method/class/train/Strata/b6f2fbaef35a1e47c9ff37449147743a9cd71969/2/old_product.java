public static <R> Result<R> ofNullable(@Nullable R value) {
    if (value != null) {
      return success(value);
    } else {
      return failure(FailureReason.MISSING_DATA, "Found null where a value was expected");
    }
  }