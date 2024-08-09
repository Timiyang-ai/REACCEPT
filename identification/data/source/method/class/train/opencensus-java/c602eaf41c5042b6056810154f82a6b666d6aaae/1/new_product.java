public final void handleMessageSent(HttpRequestContext context, long bytes) {
    checkNotNull(context, "context");
    context.sentMessageSize.addAndGet(bytes);
    if (context.span.getOptions().contains(Options.RECORD_EVENTS)) {
      // record compressed size
      recordMessageEvent(context.span, context.sentSeqId.addAndGet(1L), Type.SENT, bytes, 0L);
    }
  }