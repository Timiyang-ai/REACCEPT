public static <R> Result<R> ofNullable(@Nullable R value) {
    return ofNullable(value, FailureReason.MISSING_DATA, "Found null where a value was expected");
  }