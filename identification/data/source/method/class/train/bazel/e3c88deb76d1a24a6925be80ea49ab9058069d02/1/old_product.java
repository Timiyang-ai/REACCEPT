public static void invalidate(
      QueryableGraph graph,
      Iterable<SkyKey> diff,
      EvaluationProgressReceiver invalidationReceiver,
      InvalidationState state,
      DirtyKeyTracker dirtyKeyTracker)
      throws InterruptedException {
    invalidate(graph, diff, invalidationReceiver, state, dirtyKeyTracker,
        AbstractQueueVisitor.EXECUTOR_FACTORY);
  }