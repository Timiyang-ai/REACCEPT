@ExperimentalApi("https://github.com/grpc/grpc-java/issues/1975")
  public static Status statusFromCancelled(Context context) {
    Preconditions.checkNotNull(context, "context must not be null");
    if (!context.isCancelled()) {
      return null;
    }

    Throwable cancellationCause = context.cancellationCause();
    if (cancellationCause == null) {
      return Status.CANCELLED.withDescription("io.grpc.Context was cancelled without error");
    }
    if (cancellationCause instanceof TimeoutException) {
      return Status.DEADLINE_EXCEEDED
          .withDescription(cancellationCause.getMessage())
          .withCause(cancellationCause);
    }
    Status status = Status.fromThrowable(cancellationCause);
    if (Status.Code.UNKNOWN.equals(status.getCode())
        && status.getCause() == cancellationCause) {
      // If fromThrowable could not determine a status, then
      // just return CANCELLED.
      return Status.CANCELLED.withDescription("Context cancelled").withCause(cancellationCause);
    }
    return status.withCause(cancellationCause);
  }