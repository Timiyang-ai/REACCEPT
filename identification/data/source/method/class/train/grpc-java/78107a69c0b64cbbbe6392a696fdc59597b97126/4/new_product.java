protected void transportHeadersReceived(Metadata headers) {
    Preconditions.checkNotNull(headers, "headers");
    if (transportError != null) {
      // Already received a transport error so just augment it. Something is really, really strange.
      transportError = transportError.augmentDescription("headers: " + headers);
      return;
    }
    try {
      if (headersReceived) {
        transportError = Status.INTERNAL.withDescription("Received headers twice");
        return;
      }
      Integer httpStatus = headers.get(HTTP2_STATUS);
      if (httpStatus != null && httpStatus >= 100 && httpStatus < 200) {
        // Ignore the headers. See RFC 7540 §8.1
        return;
      }
      headersReceived = true;

      transportError = validateInitialMetadata(headers);
      if (transportError != null) {
        return;
      }

      stripTransportDetails(headers);
      inboundHeadersReceived(headers);
    } finally {
      if (transportError != null) {
        // Note we don't immediately report the transport error, instead we wait for more data on
        // the stream so we can accumulate more detail into the error before reporting it.
        transportError = transportError.augmentDescription("headers: " + headers);
        transportErrorMetadata = headers;
        errorCharset = extractCharset(headers);
      }
    }
  }