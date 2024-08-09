public static void invalidate(
      QueryableGraph graph,
      Iterable<SkyKey> diff,
      EvaluationProgressReceiver invalidationReceiver,
      InvalidationState state,
      DirtyKeyTracker dirtyKeyTracker,
      ForkJoinPool forkJoinPool,
      boolean supportInterruptions)
      throws InterruptedException {
    DirtyingNodeVisitor visitor =
        createInvalidatingVisitorIfNeeded(
            graph,
            diff,
            invalidationReceiver,
            state,
            dirtyKeyTracker,
            forkJoinPool,
            supportInterruptions,
            ErrorHandler.NullHandler.INSTANCE);
    if (visitor != null) {
      visitor.run();
    }
  }