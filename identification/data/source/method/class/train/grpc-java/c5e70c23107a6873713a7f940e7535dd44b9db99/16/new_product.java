protected void inboundHeadersReceived(Metadata.Headers headers) {
    if (inboundPhase() == Phase.STATUS) {
      log.log(Level.INFO, "Received headers on closed stream {0} {1}",
          new Object[]{id(), headers});
    }
    inboundPhase(Phase.MESSAGE);
    delayDeframer(listener.headersRead(headers));
  }