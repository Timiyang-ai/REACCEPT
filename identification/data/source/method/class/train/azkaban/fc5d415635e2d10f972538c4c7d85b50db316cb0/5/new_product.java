public void enqueueAll(
      final Collection<Pair<ExecutionReference, ExecutableFlow>> collection)
      throws ExecutorManagerException {
    for (final Pair<ExecutionReference, ExecutableFlow> pair : collection) {
      enqueue(pair.getSecond(), pair.getFirst());
    }
  }