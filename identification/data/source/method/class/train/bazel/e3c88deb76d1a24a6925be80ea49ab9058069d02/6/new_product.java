public static void invalidate(
      QueryableGraph graph,
      Iterable<SkyKey> diff,
      DirtyTrackingProgressReceiver progressReceiver,
      InvalidationState state,
      Function<ExecutorParams, ? extends ExecutorService> executorFactory)
      throws InterruptedException {
    DirtyingNodeVisitor visitor =
        createInvalidatingVisitorIfNeeded(
            graph, diff, progressReceiver, state, executorFactory);
    if (visitor != null) {
      visitor.run();
    }
  }