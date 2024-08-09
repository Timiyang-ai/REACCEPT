public ExecutableFlow getFlow(int executionId) {
    if (hasExecution(executionId)) {
      return queuedFlowMap.get(executionId).getSecond();
    }
    return null;
  }