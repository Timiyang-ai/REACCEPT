protected void inboundHeadersReceived(Metadata.Headers headers) {
    if (state() == CLOSED) {
      log.log(Level.INFO, "Received headers on closed stream {0} {1}",
          new Object[]{id(), headers});
    }
    inboundPhase(Phase.MESSAGE);
    if (GRPC_V2_PROTOCOL) {
      ListenableFuture<?> future = deframer2.delayProcessing(listener.headersRead(headers));
      if (future != null) {
        Futures.addCallback(future, failureCallback);
      }
    } else {
      // This is a little broken as it doesn't strictly wait for the last payload handled
      // by the deframer to be processed by the application layer. Not worth fixing as will
      // be removed when the old deframer is removed.
      listener.headersRead(headers);
    }
  }