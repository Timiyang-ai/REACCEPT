public final void handleMessageReceived(HttpRequestContext context, long bytes) {
    checkNotNull(context, "context");
    context.receiveMessageSize.addAndGet(bytes);
    if (context.span.getOptions().contains(Options.RECORD_EVENTS)) {
      // record compressed size
      recordMessageEvent(
          context.span, context.receviedSeqId.addAndGet(1L), Type.RECEIVED, bytes, 0L);
    }
  }