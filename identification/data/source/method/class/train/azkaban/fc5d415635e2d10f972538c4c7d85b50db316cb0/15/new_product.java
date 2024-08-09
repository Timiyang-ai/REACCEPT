public void dequeue(final int executionId) {
    if (this.queuedFlowMap.containsKey(executionId)) {
      this.queuedFlowList.remove(this.queuedFlowMap.get(executionId));
      this.queuedFlowMap.remove(executionId);
    }
  }