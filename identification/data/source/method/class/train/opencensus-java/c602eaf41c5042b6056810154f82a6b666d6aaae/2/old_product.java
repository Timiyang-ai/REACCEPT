public final void handleMessageReceived(Span span, long messageId, long messageSize) {
    checkNotNull(span, "span");
    // record compressed size
    recordMessageEvent(span, messageId, Type.RECEIVED, messageSize, 0L);
  }