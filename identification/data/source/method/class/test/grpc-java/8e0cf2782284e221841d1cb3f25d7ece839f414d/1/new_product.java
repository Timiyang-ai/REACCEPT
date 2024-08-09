public static ConnectivityStateInfo forTransientFailure(Status error) {
    Preconditions.checkArgument(!error.isOk(), "The error status must not be OK");
    return new ConnectivityStateInfo(TRANSIENT_FAILURE, error);
  }