public static void invalidate(
      QueryableGraph graph,
      Iterable<SkyKey> diff,
      DirtyTrackingProgressReceiver progressReceiver,
      InvalidationState state)
      throws InterruptedException {
    invalidate(graph, diff, progressReceiver, state, AbstractQueueVisitor.EXECUTOR_FACTORY);
  }