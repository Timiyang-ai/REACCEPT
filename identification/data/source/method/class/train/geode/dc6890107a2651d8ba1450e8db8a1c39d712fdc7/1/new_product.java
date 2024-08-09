public <A extends ClusterManagementOperation<V>, V extends OperationResult> OperationInstance<A, V> save(
      OperationInstance<A, V> operationInstance) {
    String opId = operationInstance.getId();
    CompletableFuture<V> future = operationInstance.getFutureResult();

    future.whenComplete((result, exception) -> operationInstance.setOperationEnded(new Date()));

    history.put(opId, operationInstance);
    expireHistory();

    return operationInstance;
  }