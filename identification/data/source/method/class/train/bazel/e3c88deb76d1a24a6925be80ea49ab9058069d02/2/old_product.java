public static void invalidate(
      QueryableGraph graph,
      Iterable<SkyKey> diff,
      EvaluationProgressReceiver progressReceiver,
      InvalidationState state,
      DirtyKeyTracker dirtyKeyTracker)
      throws InterruptedException {
    invalidate(graph, diff, progressReceiver, state, dirtyKeyTracker,
        AbstractQueueVisitor.EXECUTOR_FACTORY);
  }