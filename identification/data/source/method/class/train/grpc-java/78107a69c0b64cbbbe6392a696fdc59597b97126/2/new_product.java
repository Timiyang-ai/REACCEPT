public static Status httpStatusToGrpcStatus(int httpStatusCode) {
    return httpStatusToGrpcCode(httpStatusCode).toStatus()
        .withDescription("HTTP status code " + httpStatusCode);
  }