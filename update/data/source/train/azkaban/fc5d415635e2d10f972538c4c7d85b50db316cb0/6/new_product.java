public boolean hasExecution(final int executionId) {
    return this.queuedFlowMap.containsKey(executionId);
  }