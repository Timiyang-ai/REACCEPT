protected void inboundHeadersReceived(Metadata.Headers headers) {
    if (inboundPhase() == Phase.STATUS) {
      log.log(Level.INFO, "Received headers on closed stream {0} {1}",
          new Object[]{id(), headers});
    }
    if (headers.containsKey(ChannelImpl.MESSAGE_ENCODING_KEY)) {
      String messageEncoding = headers.get(ChannelImpl.MESSAGE_ENCODING_KEY);
      setDecompressor(messageEncoding);
    }
    inboundPhase(Phase.MESSAGE);
    listener.headersRead(headers);
  }