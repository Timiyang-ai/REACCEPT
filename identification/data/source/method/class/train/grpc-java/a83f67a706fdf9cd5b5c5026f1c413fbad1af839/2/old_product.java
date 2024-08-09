@VisibleForTesting
  final Metadata updateHeaders(Metadata originalHeaders, int previousAttempts) {
    Metadata newHeaders = originalHeaders;
    if (previousAttempts > 0) {
      newHeaders = new Metadata();
      newHeaders.merge(originalHeaders);
      newHeaders.put(GRPC_PREVIOUS_RPC_ATTEMPTS, String.valueOf(previousAttempts));
    }
    return newHeaders;
  }