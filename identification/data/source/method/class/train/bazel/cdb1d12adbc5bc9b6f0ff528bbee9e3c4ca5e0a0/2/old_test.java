  private static Set<SkyKey> setValue(
      NodeEntry entry, SkyValue value, @Nullable ErrorInfo errorInfo, long graphVersion)
      throws InterruptedException {
    return entry.setValue(
        ValueWithMetadata.normal(value, errorInfo, NO_EVENTS, NO_POSTS),
        IntVersion.of(graphVersion));
  }