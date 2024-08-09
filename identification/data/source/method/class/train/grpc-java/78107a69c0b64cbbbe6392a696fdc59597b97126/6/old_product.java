protected void transportDataReceived(ReadableBuffer frame, boolean endOfStream) {
    if (transportError != null) {
      // We've already detected a transport error and now we're just accumulating more detail
      // for it.
      transportError = transportError.augmentDescription("DATA-----------------------------\n"
          + ReadableBuffers.readAsString(frame, errorCharset));
      frame.close();
      if (transportError.getDescription().length() > 1000 || endOfStream) {
        http2ProcessingFailed(transportError, transportErrorMetadata);
      }
    } else {
      if (!headersReceived) {
        http2ProcessingFailed(
            Status.INTERNAL.withDescription("headers not received before payload"),
            new Metadata());
        return;
      }
      inboundDataReceived(frame);
      if (endOfStream) {
        // This is a protocol violation as we expect to receive trailers.
        transportError =
            Status.INTERNAL.withDescription("Received unexpected EOS on DATA frame from server.");
        transportErrorMetadata = new Metadata();
        transportReportStatus(transportError, false, transportErrorMetadata);
      }
    }
  }