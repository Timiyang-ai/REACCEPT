public void dequeue(int executionId) {
    if (queuedFlowMap.containsKey(executionId)) {
      queuedFlowList.remove(queuedFlowMap.get(executionId));
      queuedFlowMap.remove(executionId);
    }
  }