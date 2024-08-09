public static void invalidate(
      InvalidatableGraph graph,
      Iterable<SkyKey> diff,
      EvaluationProgressReceiver invalidationReceiver,
      InvalidationState state,
      DirtyKeyTracker dirtyKeyTracker,
      ForkJoinPool forkJoinPool,
      boolean supportInterruptions,
      ErrorHandler errorHandler)
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
            errorHandler);
    if (visitor != null) {
      visitor.run();
    }
  }