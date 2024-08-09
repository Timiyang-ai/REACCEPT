public Pair<ExecutionReference, ExecutableFlow> fetchHead()
      throws InterruptedException {
    final Pair<ExecutionReference, ExecutableFlow> pair = this.queuedFlowList.take();
    if (pair != null && pair.getFirst() != null) {
      this.queuedFlowMap.remove(pair.getFirst().getExecId());
    }
    return pair;
  }