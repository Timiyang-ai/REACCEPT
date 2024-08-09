public final SpanBuilder spanBuilderWithRemoteParent(
      @Nullable SpanContext remoteParent, String name) {
    return SpanBuilder.builderWithRemoteParent(
        spanFactory, remoteParent, checkNotNull(name, "name"));
  }