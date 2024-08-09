protected void inboundHeadersReceived(Metadata headers) {
    checkState(listener != null, "stream not started");
    if (inboundPhase() == Phase.STATUS) {
      log.log(Level.INFO, "Received headers on closed stream {0} {1}",
          new Object[]{id(), headers});
    }
    if (headers.containsKey(GrpcUtil.MESSAGE_ENCODING_KEY)) {
      String messageEncoding = headers.get(GrpcUtil.MESSAGE_ENCODING_KEY);
      try {
        setDecompressor(messageEncoding);
      } catch (IllegalArgumentException e) {
        // Don't use INVALID_ARGUMENT since that is for servers to send clients.
        Status status = Status.INTERNAL.withDescription("Unable to decompress message from server.")
            .withCause(e);
        // TODO(carl-mastrangelo): look back into tearing down this stream.  sendCancel() can be
        // buffered.
        inboundTransportError(status);
        sendCancel(status);
        return;
      }
    }

    inboundPhase(Phase.MESSAGE);
    listener.headersRead(headers);
  }