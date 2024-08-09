public void clear() {
    for (final Pair<ExecutionReference, ExecutableFlow> pair : this.queuedFlowMap.values()) {
      dequeue(pair.getFirst().getExecId());
    }
  }