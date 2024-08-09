@VisibleForTesting
  final Metadata updateHeaders(
      Metadata originalHeaders, int previousAttemptCount) {
    Metadata newHeaders = new Metadata();
    newHeaders.merge(originalHeaders);
    if (previousAttemptCount > 0) {
      newHeaders.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(previousAttemptCount));
    }
    return newHeaders;
  }