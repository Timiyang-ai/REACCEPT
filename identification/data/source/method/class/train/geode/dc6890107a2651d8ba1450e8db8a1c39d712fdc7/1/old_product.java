public <A extends ClusterManagementOperation<V>, V extends JsonSerializable> OperationInstance<A, V> save(
      OperationInstance<A, V> operationInstance) {
    String opId = operationInstance.getId();
    CompletableFuture<V> future = operationInstance.getFuture();

    inProgressHistory.put(opId, future);

    CompletableFuture<V> newFuture = future.whenComplete((result, exception) -> {
      completedHistory.put(opId, future);
      inProgressHistory.remove(opId);
    });

    // we want to replace only if still in in-progress.
    inProgressHistory.replace(opId, future, newFuture);

    return new OperationInstance<>(newFuture, opId, operationInstance.getOperation());
  }