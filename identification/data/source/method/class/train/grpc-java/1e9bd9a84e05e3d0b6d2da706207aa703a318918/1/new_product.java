@Nullable
  public static com.google.rpc.Status fromThrowable(Throwable t) {
    Throwable cause = checkNotNull(t, "t");
    while (cause != null) {
      if (cause instanceof StatusException) {
        StatusException e = (StatusException) cause;
        return fromStatusAndTrailers(e.getStatus(), e.getTrailers());
      } else if (cause instanceof StatusRuntimeException) {
        StatusRuntimeException e = (StatusRuntimeException) cause;
        return fromStatusAndTrailers(e.getStatus(), e.getTrailers());
      }
      cause = cause.getCause();
    }
    return null;
  }