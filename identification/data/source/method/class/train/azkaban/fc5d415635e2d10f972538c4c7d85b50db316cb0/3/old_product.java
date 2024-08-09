public Pair<ExecutionReference, ExecutableFlow> fetchHead()
    throws InterruptedException {
    Pair<ExecutionReference, ExecutableFlow> pair = queuedFlowList.take();
    if (pair != null && pair.getFirst() != null) {
      queuedFlowMap.remove(pair.getFirst().getExecId());
    }
    return pair;
  }