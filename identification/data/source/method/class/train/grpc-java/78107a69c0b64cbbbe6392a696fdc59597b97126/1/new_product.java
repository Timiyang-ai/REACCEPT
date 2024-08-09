protected void transportTrailersReceived(Metadata trailers) {
    Preconditions.checkNotNull(trailers, "trailers");
    if (transportError == null && !headersReceived) {
      transportError = validateInitialMetadata(trailers);
      if (transportError != null) {
        transportErrorMetadata = trailers;
      }
    }
    if (transportError != null) {
      transportError = transportError.augmentDescription("trailers: " + trailers);
      http2ProcessingFailed(transportError, transportErrorMetadata);
    } else {
      Status status = statusFromTrailers(trailers);
      stripTransportDetails(trailers);
      inboundTrailersReceived(trailers, status);
    }
  }