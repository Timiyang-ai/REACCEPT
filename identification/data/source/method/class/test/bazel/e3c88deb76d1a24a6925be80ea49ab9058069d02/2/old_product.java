public static void invalidate(
      QueryableGraph graph,
      Iterable<SkyKey> diff,
      EvaluationProgressReceiver invalidationReceiver,
      InvalidationState state,
      DirtyKeyTracker dirtyKeyTracker,
      Function<ExecutorParams, ? extends ExecutorService> executorFactory)
      throws InterruptedException {
    DirtyingNodeVisitor visitor =
        createInvalidatingVisitorIfNeeded(
            graph, diff, invalidationReceiver, state, dirtyKeyTracker, executorFactory);
    if (visitor != null) {
      visitor.run();
    }
  }