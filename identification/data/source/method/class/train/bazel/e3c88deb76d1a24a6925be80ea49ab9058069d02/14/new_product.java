public static void invalidate(
      QueryableGraph graph,
      Iterable<SkyKey> diff,
      DirtyTrackingProgressReceiver progressReceiver,
      InvalidationState state)
      throws InterruptedException {
    DirtyingNodeVisitor visitor =
        createInvalidatingVisitorIfNeeded(graph, diff, progressReceiver, state);
    if (visitor != null) {
      visitor.run();
    }
  }