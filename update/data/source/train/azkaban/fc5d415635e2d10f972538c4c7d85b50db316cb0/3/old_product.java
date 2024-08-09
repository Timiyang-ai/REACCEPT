public void clear() {
    for (Pair<ExecutionReference, ExecutableFlow> pair : queuedFlowMap.values()) {
      dequeue(pair.getFirst().getExecId());
    }
  }