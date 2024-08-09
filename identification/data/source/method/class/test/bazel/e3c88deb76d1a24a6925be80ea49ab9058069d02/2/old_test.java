  @SuppressWarnings("unused") // Overridden by subclasses.
  void invalidate(
      InMemoryGraph graph, DirtyTrackingProgressReceiver progressReceiver, SkyKey... keys)
      throws InterruptedException {
    throw new UnsupportedOperationException();
  }