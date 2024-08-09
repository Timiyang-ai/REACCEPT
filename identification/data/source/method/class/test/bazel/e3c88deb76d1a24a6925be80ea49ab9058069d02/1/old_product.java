public static void invalidate(
      ThinNodeQueryableGraph graph,
      Iterable<SkyKey> diff,
      EvaluationProgressReceiver invalidationReceiver,
      InvalidationState state,
      DirtyKeyTracker dirtyKeyTracker,
      ForkJoinPool forkJoinPool)
      throws InterruptedException {
    DirtyingNodeVisitor visitor =
        createInvalidatingVisitorIfNeeded(
            graph, diff, invalidationReceiver, state, dirtyKeyTracker, forkJoinPool);
    if (visitor != null) {
      visitor.run();
    }
  }