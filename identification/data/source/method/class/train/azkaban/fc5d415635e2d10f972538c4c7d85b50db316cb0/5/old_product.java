public void enqueueAll(
    Collection<Pair<ExecutionReference, ExecutableFlow>> collection)
    throws ExecutorManagerException {
    for (Pair<ExecutionReference, ExecutableFlow> pair : collection) {
      enqueue(pair.getSecond(), pair.getFirst());
    }
  }