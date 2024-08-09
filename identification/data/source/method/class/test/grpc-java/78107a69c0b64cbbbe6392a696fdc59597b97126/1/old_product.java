public static Status httpStatusToGrpcStatus(int httpStatusCode) {
    // Specific HTTP code handling.
    switch (httpStatusCode) {
      case HttpURLConnection.HTTP_UNAUTHORIZED:  // 401
        return Status.UNAUTHENTICATED;
      case HttpURLConnection.HTTP_FORBIDDEN:  // 403
        return Status.PERMISSION_DENIED;
      default:
    }
    // Generic HTTP code handling.
    if (httpStatusCode < 100) {
      // 0xx. These don't exist.
      return Status.UNKNOWN;
    }
    if (httpStatusCode < 200) {
      // 1xx. These headers should have been ignored.
      return Status.INTERNAL;
    }
    if (httpStatusCode < 300) {
      // 2xx
      return Status.OK;
    }
    return Status.UNKNOWN;
  }