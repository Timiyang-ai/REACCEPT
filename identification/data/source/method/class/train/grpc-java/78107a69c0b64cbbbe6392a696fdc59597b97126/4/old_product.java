protected void transportHeadersReceived(Metadata headers) {
    Preconditions.checkNotNull(headers, "headers");
    if (transportError != null) {
      // Already received a transport error so just augment it.
      transportError = transportError.augmentDescription(headers.toString());
      return;
    }
    Status httpStatus = statusFromHttpStatus(headers);
    if (httpStatus == null) {
      transportError = Status.INTERNAL.withDescription(
          "received non-terminal headers with no :status");
    } else if (!httpStatus.isOk()) {
      transportError = httpStatus;
    } else {
      transportError = checkContentType(headers);
    }
    if (transportError != null) {
      // Note we don't immediately report the transport error, instead we wait for more data on the
      // stream so we can accumulate more detail into the error before reporting it.
      transportError = transportError.augmentDescription("\n" + headers);
      transportErrorMetadata = headers;
      errorCharset = extractCharset(headers);
    } else {
      stripTransportDetails(headers);
      inboundHeadersReceived(headers);
    }
  }