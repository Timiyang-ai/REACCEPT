protected void inboundHeadersReceived(Metadata.Headers headers) {
    if (state() == CLOSED) {
      log.log(Level.INFO, "Received headers on closed stream {0} {1}",
          new Object[]{id(), headers});
    }
    inboundPhase(Phase.MESSAGE);
    delayDeframer(listener.headersRead(headers));
  }