protected void inboundHeadersReceived(Metadata headers) {
    checkState(listener != null, "stream not started");
    if (inboundPhase() == Phase.STATUS) {
      log.log(Level.INFO, "Received headers on closed stream {0} {1}",
          new Object[]{id(), headers});
    }

    inboundPhase(Phase.MESSAGE);
    listener.headersRead(headers);
  }