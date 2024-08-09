public ExecutableFlow getFlow(final int executionId) {
    if (hasExecution(executionId)) {
      return this.queuedFlowMap.get(executionId).getSecond();
    }
    return null;
  }