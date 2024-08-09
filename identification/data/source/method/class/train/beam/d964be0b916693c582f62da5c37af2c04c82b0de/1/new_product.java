public synchronized void setWorker(
      DataflowWorkExecutor worker, BatchModeExecutionContext executionContext) {
    checkArgument(worker != null, "worker must be non-null");
    checkState(this.worker == null, "Can only call setWorker once");
    this.worker = worker;
    this.executionContext = executionContext;
  }