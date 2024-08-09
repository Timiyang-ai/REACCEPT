public SpanBuilder spanBuilderWithRemoteParent(@Nullable SpanContext remoteParent, String name) {
    return new SpanBuilder(
        spanFactory, remoteParent, /* hasRemoteParent = */ true, checkNotNull(name, "name"));
  }