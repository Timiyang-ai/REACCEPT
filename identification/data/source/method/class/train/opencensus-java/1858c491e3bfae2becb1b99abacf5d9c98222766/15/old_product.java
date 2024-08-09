public SpanBuilder spanBuilderWithRemoteParent(@Nullable SpanContext remoteParent, String name) {
    return new SpanBuilder(
        spanFactory,
        contextSpanHandler,
        remoteParent,
        /* hasRemoteParent = */ true,
        checkNotNull(name, "name"));
  }