public static void invalidate(DirtiableGraph graph, Iterable<SkyKey> diff,
      EvaluationProgressReceiver invalidationReceiver, InvalidationState state,
      DirtyKeyTracker dirtyKeyTracker)
          throws InterruptedException {
    // If we are invalidating, we must be in an incremental build by definition, so we must
    // maintain a consistent graph state by traversing the graph and invalidating transitive
    // dependencies. If edges aren't present, it would be impossible to check the dependencies of
    // a dirty node in any case.
    InvalidatingNodeVisitor visitor =
        createVisitor(/*delete=*/false, graph, diff, invalidationReceiver, state,
            /*traverseGraph=*/true, dirtyKeyTracker);
    if (visitor != null) {
      visitor.run();
    }
  }