public synchronized void setWorker(
      DataflowWorkExecutor worker, BatchModeExecutionContext executionContext) {
    checkState(this.worker == null, "Can only call setWorker once");
    this.worker = checkNotNull(worker, "worker must be non-null");
    this.executionContext = executionContext;
  }