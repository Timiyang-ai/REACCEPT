public final void handleMessageSent(Span span, long messageId, long messageSize) {
    checkNotNull(span, "span");
    // record compressed size
    recordMessageEvent(span, messageId, Type.SENT, messageSize, 0L);
  }