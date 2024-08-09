public static void invalidate(
      QueryableGraph graph,
      Iterable<SkyKey> diff,
      DirtyTrackingProgressReceiver progressReceiver,
      InvalidationState state,
      ForkJoinPool forkJoinPool,
      boolean supportInterruptions)
      throws InterruptedException {
    DirtyingNodeVisitor visitor =
        createInvalidatingVisitorIfNeeded(
            graph,
            diff,
            progressReceiver,
            state,
            forkJoinPool,
            supportInterruptions,
            ErrorHandler.NullHandler.INSTANCE);
    if (visitor != null) {
      visitor.run();
    }
  }