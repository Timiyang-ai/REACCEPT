public final void handleMessageSent(Span span, long messageId, long messageSize) {
    checkNotNull(span, "span");
    if (span.getOptions().contains(Options.RECORD_EVENTS)) {
      // record compressed size
      recordMessageEvent(span, messageId, Type.SENT, messageSize, 0L);
    }
  }