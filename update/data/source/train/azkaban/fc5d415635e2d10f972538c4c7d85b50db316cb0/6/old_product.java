public boolean hasExecution(int executionId) {
    return queuedFlowMap.containsKey(executionId);
  }