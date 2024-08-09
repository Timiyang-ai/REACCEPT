  private static <A extends ClusterManagementOperation<V>, V extends OperationResult> OperationInstance<A, V> op(
      String id, CompletableFuture<V> future) {
    return op(id, future, new Date());
  }