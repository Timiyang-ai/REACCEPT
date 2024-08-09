public static void invalidate(
      QueryableGraph graph,
      Iterable<SkyKey> diff,
      EvaluationProgressReceiver progressReceiver,
      InvalidationState state,
      DirtyKeyTracker dirtyKeyTracker,
      ForkJoinPool forkJoinPool,
      boolean supportInterruptions)
      throws InterruptedException {
    DirtyingNodeVisitor visitor =
        createInvalidatingVisitorIfNeeded(
            graph,
            diff,
            progressReceiver,
            state,
            dirtyKeyTracker,
            forkJoinPool,
            supportInterruptions,
            ErrorHandler.NullHandler.INSTANCE);
    if (visitor != null) {
      visitor.run();
    }
  }