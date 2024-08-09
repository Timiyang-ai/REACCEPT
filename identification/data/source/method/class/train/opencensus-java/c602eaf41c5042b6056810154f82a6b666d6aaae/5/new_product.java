final void handleMessageReceived(Span span, long messageId, long messageSize) {
    checkNotNull(span, "span");
    if (span.getOptions().contains(Options.RECORD_EVENTS)) {
      // record compressed size
      recordMessageEvent(span, messageId, Type.RECEIVED, messageSize, 0L);
    }
  }