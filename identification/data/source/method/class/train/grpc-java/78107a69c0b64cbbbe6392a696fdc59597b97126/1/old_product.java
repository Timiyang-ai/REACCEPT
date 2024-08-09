protected void transportTrailersReceived(Metadata trailers) {
    Preconditions.checkNotNull(trailers, "trailers");
    if (transportError != null) {
      // Already received a transport error so just augment it.
      transportError = transportError.augmentDescription(trailers.toString());
    } else {
      transportError = checkContentType(trailers);
      transportErrorMetadata = trailers;
    }
    if (transportError != null) {
      http2ProcessingFailed(transportError, transportErrorMetadata);
    } else {
      Status status = statusFromTrailers(trailers);
      stripTransportDetails(trailers);
      inboundTrailersReceived(trailers, status);
    }
  }