public static void invalidate(
      QueryableGraph graph,
      Iterable<SkyKey> diff,
      EvaluationProgressReceiver progressReceiver,
      InvalidationState state,
      DirtyKeyTracker dirtyKeyTracker,
      Function<ExecutorParams, ? extends ExecutorService> executorFactory)
      throws InterruptedException {
    DirtyingNodeVisitor visitor =
        createInvalidatingVisitorIfNeeded(
            graph, diff, progressReceiver, state, dirtyKeyTracker, executorFactory);
    if (visitor != null) {
      visitor.run();
    }
  }